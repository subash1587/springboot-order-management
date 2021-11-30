package com.order.ordermanagement;

public enum PizzaStatus {
	ORDER(1),
	READY(2),
	DELIVERED(3);
	
	int rank;
	
	private PizzaStatus(int rank) {
		this.rank = rank;
	}
	
	
	public void testMethod() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.rank + " subash " +super.toString();
	}
}
