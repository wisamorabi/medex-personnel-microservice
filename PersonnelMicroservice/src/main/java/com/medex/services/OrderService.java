package com.medex.services;


import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.OrderDB;
import com.medex.database.PersonnelDB;
import com.medex.dependentresources.Ordr;
import com.medex.model.Personnel;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class OrderService {
	OrderDB orderdb = new OrderDB(); //(Instead of the pseudo-database)
	PersonnelDB personneldb = new PersonnelDB(); //(Instead of the pseudo-database)
	PersonnelService personnelService = new PersonnelService();

	public OrderService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<Ordr>getAllOrders()
	{
		return orderdb.getOrders(); //Get all hosts.

	}
	
	public Ordr getOrder(int orderid)
	{
		return orderdb.getOrder(orderid);
	}
	

	public Ordr updateOrder(int personnelid, int orderid, Ordr order)
	{
		if (personneldb.getPersonnel(personnelid) == null) return null;
		if (personneldb.getPersonnel(personnelid).getOrderid() != orderid) return null;
		if (orderdb.getOrder(orderid) == null) return null;
		if (order.getDone() == true)
		{
			Personnel apersonnel = personnelService.getPersonnel(personnelid);
			apersonnel.setOrderid(-1);
			personnelService.updatePersonnel(apersonnel);
		}
		orderdb.updateOrder(order);
		return order;
	}
	public Status checkOrder(int personnelid, int orderid)
	{

		if (orderdb.getOrder(orderid).getDone() == true)
		{
			Personnel apersonnel = personnelService.getPersonnel(personnelid);
			apersonnel.setOrderid(-1);
			personnelService.updatePersonnel(apersonnel);
			return new Status(true);
		}
		return new Status(false);
			
	}
	public Status attachOrder(int personnelid)
	{
		if (personnelService.getPersonnel(personnelid) == null) return new Status(false);
		if (personnelService.getPersonnel(personnelid).getOrderid() != -1) return new Status(false);
		for (Ordr o : getAllOrders())
		{
			System.out.println(o.getDone() + " " + o.getInProgress());
			if (o.getDone() == false && o.getInProgress() == false) 
			{
				Personnel apersonnel = personnelService.getPersonnel(personnelid);
				apersonnel.setOrderid(o.getId());
				personnelService.updatePersonnel(apersonnel);
				o.setInProgress(true);
				orderdb.updateOrder(o);
				return new Status(true);
			}
			
			

		}
		return new Status(false);
	}

}


