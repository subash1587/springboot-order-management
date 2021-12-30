package com.order.ordermanagement.validation;

import org.springframework.stereotype.Component;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.ItemError;
import com.order.ordermanagement.model.ItemModel;

@Component
public class ItemValidator {

	public void validateItem(ItemModel itemModel) {
		
		if (itemModel.getName() == null) {
			throw new ApiException(ItemError.ITEM_NAME_NULL);
		}
		if (itemModel.getName().equals("")) {
			throw new ApiException(ItemError.ITEM_NAME_BLANK);
		}
		if (itemModel.getName().length() > 50) {
			throw new ApiException(ItemError.ITEM_NAME_LENGTH_EXCEEDS);
		}
		if (itemModel.getCategory().length() > 50) {
			throw new ApiException(ItemError.ITEM_CATEGORY_LENGTH_EXCEEDS);
		}
		if (itemModel.getPrice() <= 0) {
			throw new ApiException(ItemError.ITEM_PRICE_INVALID);
		}
		if(itemModel.getRating() < 0 || itemModel.getRating() > 5) {
			throw new ApiException(ItemError.ITEM_RATING_INVALID);
		}
	}
}
