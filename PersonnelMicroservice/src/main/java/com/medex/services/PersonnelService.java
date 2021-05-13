package com.medex.services;

import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.PersonnelDB;
import com.medex.model.Personnel;



//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PersonnelService {
	PersonnelDB personneldb = new PersonnelDB(); //(Instead of the pseudo-database)
	public PersonnelService() {} 
	
	//All what is below is just calling the functions belonging to the personnels' database/table.
	
	public List<Personnel>getAllPersonnels()
	{
		return personneldb.getPersonnels();
	}
	
	public Personnel getPersonnel(int id)
	{
		return personneldb.getPersonnel(id);
	}
	
	public Personnel addPersonnel(Personnel aPersonnel)
	{
		personneldb.insertPersonnel(aPersonnel); return aPersonnel;
	}
	
	public Personnel updatePersonnel(Personnel aPersonnel)
	{
		if (personneldb.getPersonnel(aPersonnel.getId()) == null) return null;
		personneldb.updatePersonnel(aPersonnel);
		return aPersonnel;
	}
	
	public Status removePersonnel(int id)
	{
		if (personneldb.getPersonnel(id) == null) return new Status(false);
		personneldb.deletePersonnel(id);
		return new Status(true);
	}
	
	
	
}


