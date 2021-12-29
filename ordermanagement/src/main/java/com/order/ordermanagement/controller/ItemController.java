package com.order.ordermanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.ItemModel;
import com.order.ordermanagement.service.AuthService;
import com.order.ordermanagement.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	ItemService itemService;

	@Autowired
	AuthService authService;

	@RequestMapping(path = "/item", method = RequestMethod.POST)
	public ResponseEntity<?> addItem(@RequestBody ItemModel itemModel) {
		itemService.addItem(itemModel);
		return ResponseEntity.accepted().build();
	}

	@RequestMapping(path = "/items", method = RequestMethod.POST)
	public ResponseEntity<?> getItems(@RequestParam(name = "sort_by", required = false) String sortBy,
			@RequestParam(name = "order_by", required = false) String orderBy,
			@RequestBody(required = false) Map<String, String> filterMap,
			@RequestParam(name = "page", required = false) Integer index,
			@RequestParam(name = "names", required = false) List<String> names) {
		List<ItemModel> itemList = itemService.getItems(sortBy, orderBy, filterMap, index, names);
		return ResponseEntity.ok(itemList);
	}

	@RequestMapping(path = "/item/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getItem(@PathVariable("id") int itemId) {
		ItemModel itemModel = itemService.getItem(itemId);
		return ResponseEntity.ok(itemModel);
	}

	@RequestMapping(path = "/item/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateItem(@PathVariable("id") int itemId,
			@RequestParam(value = "name", required = false) String name, 
			@RequestBody(required=false) ItemModel itemModel) {
		itemService.updateItem(itemId, name, itemModel);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(path = "/item/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteItem(@PathVariable("id") int itemId) {
		itemService.deleteItem(itemId);
		return ResponseEntity.accepted().build();
	}
}
