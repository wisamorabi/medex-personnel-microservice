package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.OrderDB;
import com.medex.database.PersonnelDB;
import com.medex.dependentresources.Order;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class OrderService {
	OrderDB orderdb = new OrderDB(); //(Instead of the pseudo-database)
	PersonnelDB personneldb = new PersonnelDB(); //(Instead of the pseudo-database)

	public OrderService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<Order>getAllOrders()
	{
		return orderdb.getOrders(); //Get all hosts.

	}
	
	public Order getOrder(int orderid)
	{
		return orderdb.getOrder(orderid);
	}
	

	public Order updateOrder(int personnelid, int orderid, Order order)
	{
		if (personneldb.getPersonnel(personnelid) == null) return null;
		if (personneldb.getPersonnel(personnelid).getOrderid() != orderid) return null;
		if (orderdb.getOrder(orderid) == null) return null;
		orderdb.updateOrder(order);
		return order;
	}
	

}


