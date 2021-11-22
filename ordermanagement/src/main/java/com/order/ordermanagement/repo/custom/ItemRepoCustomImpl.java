package com.order.ordermanagement.repo.custom;

import java.util.ArrayList;
import java.util.List;

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

	public List<ItemEntity> findItem(List<String> filters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ItemEntity> cr = cb.createQuery(ItemEntity.class);
		Root<ItemEntity> item = cr.from(ItemEntity.class);
		cr.select(item);
		List<Predicate> predicates = new ArrayList<>();
		for(String filterColumn : filters) {
			predicates.add(cb.gt(item.get("price"), 50));
		}
		return null;
	}

}
