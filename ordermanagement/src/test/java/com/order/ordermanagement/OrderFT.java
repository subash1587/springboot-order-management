package com.order.ordermanagement;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ordermanagement.model.OrderModel;

public class OrderFT {
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void getOrdersTest() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/order");
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			String response = EntityUtils.toString(httpResponse.getEntity());
			List<OrderModel> orders = objectMapper.readValue(response, new TypeReference<List<OrderModel>>() {
			});
			Assertions.assertEquals(orders.size(), 26);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
