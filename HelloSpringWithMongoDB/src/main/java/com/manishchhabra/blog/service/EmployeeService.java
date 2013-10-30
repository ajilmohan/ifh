package com.manishchhabra.blog.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.manishchhabra.blog.model.Employee;

@Repository
public class EmployeeService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "employee";
	
	public void addEmployee(Employee employee){
		if(!mongoTemplate.collectionExists(Employee.class)){
			mongoTemplate.createCollection(Employee.class);
		}
		employee.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(employee);
	}
	
	public List<Employee> findAll(){
		return mongoTemplate.findAll(Employee.class, COLLECTION_NAME);
	}
	
	public void deleteEmployee(Employee employee){
		mongoTemplate.remove(employee, COLLECTION_NAME);
	}
	
	public void updateEmployee(Employee employee){
		mongoTemplate.insert(employee, COLLECTION_NAME);
	}
}
