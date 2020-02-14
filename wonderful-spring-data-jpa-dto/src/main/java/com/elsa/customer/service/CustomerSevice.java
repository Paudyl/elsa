package com.elsa.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elsa.customer.entity.Customer;
import com.elsa.customer.repository.CustomerRepository;

@Service
public class CustomerSevice {
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerSevice(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAll() {
		return customerRepository.findAll();

	}

	public List<Customer> findByName(String name) {
		return customerRepository.findByName(name);
	}

	public Customer findById(int id) {
		return customerRepository.findById(id).orElse(null);
	}

	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

}
