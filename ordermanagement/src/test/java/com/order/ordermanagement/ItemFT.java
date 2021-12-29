package com.order.ordermanagement;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ordermanagement.model.ItemModel;

public class ItemFT {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void getAllItemsTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> items = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			Assertions.assertEquals(items.size(), 21);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public List<ItemModel> getItems() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> items = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			return items;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public ItemModel getItem() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/item/21");
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			String response = EntityUtils.toString(httpResponse.getEntity());
			ItemModel item = objectMapper.readValue(response, new TypeReference<ItemModel>() {
			});
			return item;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void addItemtest() {
		List<ItemModel> before = getItems();
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/item");
		try {
			String jsonRequest = "{\"name\":\"itemFT\",\"price\":85,\"category\":\"FT\",\"rating\":3}";
			StringEntity entity = new StringEntity(jsonRequest);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("content-type","application/json");
			httpClient.execute(httpPost);
			List<ItemModel> after = getItems();
			Assertions.assertEquals(before.size()+1, after.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getItemByIdTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/item/10");
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			String response = EntityUtils.toString(httpResponse.getEntity());
			ItemModel itemModel = objectMapper.readValue(response, new TypeReference<ItemModel>() {
			});
			Assertions.assertEquals(itemModel.getPrice(), 1150);
			Assertions.assertEquals(itemModel.getRating(), 0);
			Assertions.assertEquals(itemModel.getCategory(), "Home Improvement");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sortItemWithoutPaginationTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items?sort_by=name");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> itemList = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			Assertions.assertEquals(itemList.get(0).getName(), "Black raisin");
			Assertions.assertEquals(itemList.get(itemList.size()-1).getName(), "Water Bottle");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sortItemWithPaginationTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items?sort_by=price&order_by=desc&page=0");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> itemList = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			Assertions.assertEquals(itemList.get(0).getPrice(), 1150);
			Assertions.assertEquals(itemList.size(),5);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sortAndOrderItemTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items?sort_by=name&order_by=desc");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> itemList = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			Assertions.assertEquals(itemList.get(0).getName(), "Water Bottle");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void filterItemTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items");
		try {
			String jsonRequest = "{\"category\":\"Food\",\"ratinggt\":4}";
			StringEntity entity = new StringEntity(jsonRequest);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("content-type","application/json");
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> itemList = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			Assertions.assertEquals(itemList.size(), 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getItemByPageTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items?page=1");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> itemList = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			Assertions.assertEquals(itemList.size(), 5);
			Assertions.assertEquals(itemList.get(0).getId(), 6);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getItemsByNamesTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/items?names=Test1,Test3");
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> itemList = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {});
			Assertions.assertEquals(itemList.size(), 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateItemTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch("http://localhost:8080/item/20");	
		try {
			String jsonRequest = "{\"name\":\"Test1\",\"price\":43,\"category\":\"FT\",\"rating\":4}";
			StringEntity entity = new StringEntity(jsonRequest);
			httpPatch.setEntity(entity);
			httpPatch.setHeader("Accept","application/json");
			httpPatch.setHeader("content-type","application/json");
			httpClient.execute(httpPatch);
			ItemModel itemModel = getItem();
			Assertions.assertEquals(itemModel.getName(), "Test1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateItemNameTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch("http://localhost:8080/item/21?name=Test3");	
		try {
			httpClient.execute(httpPatch);
			ItemModel itemModel = getItem();
			Assertions.assertEquals(itemModel.getName(), "Test3");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteItemTest() {
		List<ItemModel> before = getItems();
		HttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/item/17");
		try {
			httpClient.execute(httpDelete);
			List<ItemModel> after = getItems();
			Assertions.assertEquals(before.size()-1, after.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
