package com.order.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.ItemModel;
import com.order.ordermanagement.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;

	@RequestMapping(path="/item", method=RequestMethod.POST)
	public ResponseEntity<?> addItem(@RequestBody ItemModel itemModel) {
		itemService.addItem(itemModel);
		return ResponseEntity.accepted().build();
	}
	
	@RequestMapping(path="/item", method=RequestMethod.GET)
	public ResponseEntity<?> getItems() {
		List<ItemModel> itemList = itemService.getAllItems();
		return ResponseEntity.ok(itemList);
	}
	
	@RequestMapping(path="sort/item", method=RequestMethod.GET)
	public ResponseEntity<?> sortItems() {
		List<ItemModel> itemList = itemService.sortItemByPrice();
		return ResponseEntity.ok(itemList);
	}
	
	@RequestMapping(path="/item/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> searchItem(@PathVariable("id") int itemId) {
		ItemModel itemModel = itemService.getItem(itemId);
		return ResponseEntity.ok(itemModel);
	}
	
	@RequestMapping(path="/item/{id}", method=RequestMethod.PATCH) 
	public ResponseEntity<?> updateItem(@PathVariable("id") int itemId, @RequestBody ItemModel itemModel) {
		itemService.updateItem(itemId, itemModel); 
		return ResponseEntity.ok().build(); 
	}
	
	@RequestMapping(path="/item/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteItem(@PathVariable("id") int itemId) {
		itemService.deleteItem(itemId);
		return ResponseEntity.accepted().build();
	}
	
	@RequestMapping(path="/item/page/{index}", method=RequestMethod.GET)
	public ResponseEntity<?> getItemsWithPagination(@PathVariable("index") int index) {
		List<ItemModel> itemList = itemService.getItemsWithPagination(index);
		return ResponseEntity.ok(itemList);
	}
	
	@RequestMapping(path="/search/item", method=RequestMethod.GET)
	public ResponseEntity<?> getItemByNameAndId(@RequestParam(name="id") int id, @RequestParam String name) {
		ItemModel itemModel = itemService.getItemByNameAndId(name,id);
		return ResponseEntity.ok(itemModel);
	}
	
	@RequestMapping(path="/item/filter/price/high/{price}", method=RequestMethod.GET)
	public ResponseEntity<?> getItemsWithHigherPrice(@PathVariable("price") double price){
		List<ItemModel> itemModelList = itemService.getItemsWithHigherPrice(price);
		return ResponseEntity.ok(itemModelList);
	}
	
	@RequestMapping(path="/item/filter", method=RequestMethod.GET)
	public ResponseEntity<?> searchItemsWithFilter(){
		List<ItemModel> itemModelList = itemService.searchItemsWithFilter();
		return ResponseEntity.ok(itemModelList);
	}
	
	@RequestMapping(path="/item/names", method=RequestMethod.GET)
	public ResponseEntity<?> getItemsByNameList(){
		List<ItemModel> itemModelList = itemService.getItemsByNameList();
		return ResponseEntity.ok(itemModelList);
	}
	
	@RequestMapping(path="/item/name/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateItemName(@PathVariable("id") int id, @RequestParam("name") String name){
		itemService.updateItemName(id, name);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/item/insert", method=RequestMethod.POST)
	public ResponseEntity<?> insertItem(@RequestBody ItemModel itemModel){
		itemService.insertItem(itemModel);
		return ResponseEntity.ok().build();
	}
}
