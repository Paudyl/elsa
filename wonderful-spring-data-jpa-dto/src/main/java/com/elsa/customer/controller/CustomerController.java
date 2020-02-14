package com.elsa.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elsa.customer.dto.CustomerCdto;
import com.elsa.customer.dto.CustomerDto;
import com.elsa.customer.dto.CustomerDtoMapper;
import com.elsa.customer.entity.Customer;
import com.elsa.customer.service.CustomerSevice;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerSevice customerSevice;

	@Autowired
	private CustomerDtoMapper customerDtoMapper;

	@RequestMapping
	public List<CustomerDto> getAll(@RequestParam(required = false) String name) {
		List<Customer> customers;
		if (name == null) {
			customers = customerSevice.getAll();
		} else {
			customers = customerSevice.findByName(name);
		}
		List<CustomerDto> result = new ArrayList<>();
		for (Customer customer : customers) {
			result.add(customerDtoMapper.mapToDto(customer));
		}
		return result;
	}

	@GetMapping("/{id}")
	public CustomerDto findCustomer(@PathVariable int id) {
		Customer customer = customerSevice.findById(id);
		if (customer != null) {
			return customerDtoMapper.mapToDto(customer);
		} else
			return null;
	}

	@PostMapping
	public void createCustomer(@RequestBody CustomerCdto cusCdto) {
		
		//Just in case if id is passed in JSON, set id to 0
		cusCdto.setId(0);
		Customer customer=customerDtoMapper.mapToCdto(cusCdto);
		customerSevice.save(customer);
	}
	
	@PutMapping("/{id}")
	public void updateCustmer(@RequestBody CustomerCdto cusCdto, @PathVariable int id) {
		Customer customer = customerSevice.findById(id);
		if (customer == null) {
			customer = new Customer();
		}
		customer.setName(cusCdto.getName());
		customer.setEmail(cusCdto.getEmail());
		customer.setAddress(cusCdto.getAddress());
		customerSevice.save(customer);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		Customer customer = customerSevice.findById(id);
		if (customer != null) {
			customerSevice.delete(customer);
		}
		System.out.println("Customer with id: " + id + " is deleted");
	}
}
