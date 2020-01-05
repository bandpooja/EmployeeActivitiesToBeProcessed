package com.letsstartcoding.springbootrestapiexample.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.letsstartcoding.springbootrestapiexample.model.Employee;

import com.letsstartcoding.springbootrestapiexample.repository.EmployeeRepository;

@Service

public class EmployeeDAO {
	@PersistenceContext
	EntityManager em;
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	/*to save an employee*/
	
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
		
	}
	
	
	
	/* search all employees*/
	
	public List<Employee> findAll(){
		Query q=em.createQuery("select e.emp_id,e.activityname,e.duration,e.time from Employee e");
		List<Employee> list=q.getResultList();
		return list;
	}
	
	
	
	//Select the 7 days statistics list which is for last 7 days activities and occcurence highest occcurence on top and lowst in bottom
	
	public List<Employee> last_7_act()
	{
		//7 days statistics list
		Query q=em.createQuery("select e.activityname ,count(*) from Employee e where e.time>=subdate(now(),7) group by e.activityname order by count(time) desc");
		
		 List<Employee> emp=new ArrayList<Employee>();
		 emp=q.getResultList();
		 //employees activities from last 24 hours
		Query q1=em.createQuery("select e.emp_id,e.activityname,e.time from Employee e where e.time>=subdate(now(),1) group by e.emp_id");
		 List<Employee> emp1=new ArrayList<Employee>();
		 emp1=q1.getResultList();
		 emp.addAll(emp1);
		 
		return emp;
	}
		
		 
		 
	 

}
