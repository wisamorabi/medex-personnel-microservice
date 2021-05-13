package com.medex.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.medex.communicationmodules.Status;
import com.medex.dependentresources.Order;
import com.medex.model.Personnel;
import com.medex.services.OrderService;
import com.medex.services.PersonnelService;



//Request resources which acts as a layer before our Personnel services
@Path("/")
public class OrderResources {
	OrderService orderService = new OrderService();
	PersonnelService personnelService = new PersonnelService();

	public OrderResources() {}


	//Get all orders and pick the first one entered for the driver if he has no orders already.
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status getOrder(@PathParam("Personnelid") int personnelid, Personnel apersonnel) {
		return orderService.attachOrder(personnelid, apersonnel);
	
	}

	//Set the order to complete, etc.
	@PUT
	@Path("{orderid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order updateOrder(@PathParam("personnelID") int personnelid, @PathParam("orderid") int orderid, Order order) {
		order.setId(orderid);
		return orderService.updateOrder(personnelid, orderid, order);
	}
}
