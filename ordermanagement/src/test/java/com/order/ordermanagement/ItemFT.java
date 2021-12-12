package com.order.ordermanagement;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
	public List<ItemModel> getItemsTest() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/item");
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<ItemModel> items = objectMapper.readValue(response, new TypeReference<List<ItemModel>>() {
			});
			//Assertions.assertEquals(items.size(), 15);
			return items;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void addItemtest() {
		
		List<ItemModel> before = getItemsTest();
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/item");
		try {
			String jsonRequest = "{\"name\":\"itemFT\",\"price\":85,\"category\":\"FT\",\"rating\":3}";
			StringEntity entity = new StringEntity(jsonRequest);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("content-type","application/json");
			HttpResponse httpResponse = httpClient.execute(httpPost);
			System.out.println(httpResponse);
			List<ItemModel> after = getItemsTest();
			Assertions.assertEquals(before.size()+1, after.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
