package com.manishchhabra.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.manishchhabra.blog.model.Employee;
import com.manishchhabra.blog.service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
    @RequestMapping(value = "/employee", method = RequestMethod.GET)  
	public String getEmployeeList(ModelMap model) {  
        model.addAttribute("personList", employeeService.findAll());  
        return "employeeList";  
    }  
    
    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)  
	public View createEmloyee(@ModelAttribute Employee employee, ModelMap model) {
    	if(StringUtils.hasText(employee.getId())) {
    		employeeService.updateEmployee(employee);
    	} else {
    		employeeService.addEmployee(employee);
    	}
    	return new RedirectView("/HelloSpringWithMongoDB/employee");  
    }
        
    @RequestMapping(value = "/employee/delete", method = RequestMethod.GET)  
	public View deletePerson(@ModelAttribute Employee employee, ModelMap model) {  
    	employeeService.deleteEmployee(employee);  
        return new RedirectView("/HelloSpringWithMongoDB/employee");  
    }   
}
