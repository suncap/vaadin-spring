package com.vaadin.training.spring.service;

import java.util.Collections;
import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class CustomerServiceBean implements CustomerService {

	@Override
	public List<Customer> getCustomers(String filter, int offset, int count) {
		return Collections.emptyList();
	}

	@Override
	public int countCustomers(String filter) {
		return 0;
	}
}
