package com.elsa.customer.dto;

import org.springframework.stereotype.Component;

import com.elsa.customer.entity.Customer;

@Component
public class CustomerDtoMapper {
	public CustomerDto mapToDto(Customer customer) {
		CustomerDto cusdto=new CustomerDto();
		cusdto.setId(customer.getId());
		cusdto.setName(customer.getName());
		cusdto.setEmail(customer.getEmail());
		return cusdto;
	}
	
	public Customer mapToCdto(CustomerCdto cusCdto) {
		Customer customer = new Customer();
		customer.setName(cusCdto.getName());
		customer.setEmail(cusCdto.getEmail());
		customer.setAddress(cusCdto.getAddress());
		return customer;
	}
}

