package com.egg.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.egg.services.entities.Customer;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.repositories.CustomerRepository;
//

public class CustomerService extends PersonService<Customer> {

	@Autowired
	private CustomerRepository customerR;
	
	public List<Customer> getAll(){
		return customerR.findAll();
	}
	
	public Customer getById(Integer id) throws ServicesException {
		Optional<Customer> custommerOpt = customerR.findById(id);
		if (custommerOpt.isEmpty()) {
			throw new ServicesException("No customer found");	
		} 
		return custommerOpt.get();
	}
	 
	 
	 public List<Customer> getByName(String name) throws ServicesException{
			if (null == name || name.isBlank()) {
				throw new ServicesException("No valid name entered");
			}		
			List<Customer> customers = customerR.findByName(name);
			return customers;
			}
	 @Transactional
	 public void create (Customer customer) throws ServicesException{
		 validateCustomer(customer);
		 customerR.save(customer);
	 }
	 @Transactional
	 public void update (Customer customer) throws ServicesException{
		  if (null == customer || null == customer.getId()) {
			throw new ServicesException("Invalid customer");
		}
		create(customer);
		  
	 }
	 @Transactional
	 public void delete (Integer id) throws ServicesException{
		 Customer customer = getById(id);
		 customer.setState(false);
		 customerR.save(customer);
	 }
	 
	 
	  private void validateCustomer(Customer customer) throws ServicesException{
		  super.validate(customer);
			if (null == customer.getDirection() || customer.getDirection().isEmpty()) {
				throw new ServicesException("No valid direction entered");
			}
	}
	  
	 
}
