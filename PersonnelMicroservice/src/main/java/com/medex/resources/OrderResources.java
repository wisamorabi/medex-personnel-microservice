package com.medex.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	public OrderResources() {}


	//Get all orders and pick a random one
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getPersonnel() {
		return orderService.getAllOrders();
	}


	@PUT
	@Path("{orderid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order updatePersonnel(@PathParam("personnelID") int personnelid, @PathParam("orderid") int orderid, Order order) {
		order.setId(orderid);
		return orderService.updateOrder(personnelid, orderid, order);
	}
}
