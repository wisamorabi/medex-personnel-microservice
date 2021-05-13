package com.medex.services;


import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.OrderDB;
import com.medex.database.PersonnelDB;
import com.medex.dependentresources.Order;
import com.medex.model.Personnel;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class OrderService {
	OrderDB orderdb = new OrderDB(); //(Instead of the pseudo-database)
	PersonnelDB personneldb = new PersonnelDB(); //(Instead of the pseudo-database)
	OrderService orderService = new OrderService();
	PersonnelService personnelService = new PersonnelService();

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
	public Status attachOrder(int personnelid, Personnel apersonnel)
	{
		if (personnelService.getPersonnel(personnelid) == null) return new Status(false);
		if (personnelService.getPersonnel(personnelid).getOrderid() != -1) return new Status(false);
		for (Order o : orderService.getAllOrders())
		{
			boolean orderObjectIsFine = false;
			if (o.getDone() == false && o.getInProgress() == false) orderObjectIsFine = true;
			
			boolean personnelObjectIsFine = true;
			if (orderObjectIsFine)
			{
				for (Personnel p : personnelService.getAllPersonnels())
				{
					if (p.getOrderid() == o.getId())
					{
						personnelObjectIsFine = false;
						break;
					}
				}
				if (personnelObjectIsFine)
				{
					apersonnel.setOrderid(o.getId());
					personnelService.updatePersonnel(apersonnel);
					return new Status(true);
				}
			}
		}
		return new Status(false);
	}

}


