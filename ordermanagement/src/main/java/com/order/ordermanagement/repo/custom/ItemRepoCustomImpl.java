package com.order.ordermanagement.repo.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.order.ordermanagement.entity.ItemEntity;

public class ItemRepoCustomImpl implements ItemRepoCustom{

	@PersistenceContext
	private EntityManager entityManager;

	public List<ItemEntity> findItem(Map<String, String> filters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ItemEntity> cr = cb.createQuery(ItemEntity.class);
		Root<ItemEntity> item = cr.from(ItemEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		for(Map.Entry<String, String> filterColumn : filters.entrySet()) {
			switch(filterColumn.getKey()){
				case "pricegt":
					predicates.add(cb.ge(item.get("price"), Integer.parseInt(filterColumn.getValue())));
					break;
				case "pricelt":
					predicates.add(cb.lt(item.get("price"), Integer.parseInt(filterColumn.getValue())));
					break;
				case "category":
					predicates.add(cb.equal(item.get("category"), filterColumn.getValue()));
					break;
				case "ratinggt":
					predicates.add(cb.ge(item.get("rating"), Integer.parseInt(filterColumn.getValue())));
					break;		
			
			}
		}
		cr.select(item).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(cr).getResultList();
	}

}
