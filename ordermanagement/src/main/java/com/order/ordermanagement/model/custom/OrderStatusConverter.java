package com.order.ordermanagement.model.custom;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String>{

	@Override
	public String convertToDatabaseColumn(OrderStatus status) {
		if(status == null) {
			return null;
		}
		return status.getStatus();
	}

	@Override
	public OrderStatus convertToEntityAttribute(String status) {
		if(status == null) {
			return null;
		}
		return Stream.of(OrderStatus.values())
				.filter(s -> s.getStatus().equals(status))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
