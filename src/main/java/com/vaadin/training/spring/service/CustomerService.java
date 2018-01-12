package com.vaadin.training.spring.service;

import java.util.List;

public interface CustomerService {

	List<Customer> getCustomers(String filter, int offset, int count);

	int countCustomers(String filter);
}
