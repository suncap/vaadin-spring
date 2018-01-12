package com.vaadin.training.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.training.spring.menu.MainMenu;
import com.vaadin.training.spring.menu.MainMenuBean;
import com.vaadin.training.spring.menu.MenuItem;
import com.vaadin.training.spring.service.Customer;
import com.vaadin.training.spring.service.CustomerService;
import com.vaadin.training.spring.views.FilterComponent;
import com.vaadin.ui.Grid;

@Configuration
public class AppConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Bean
	@Scope("prototype")
	protected Grid<Customer> produceGrid(FilterComponent filter, CustomerService customerService) {
		Grid<Customer> grid = new Grid<>();

		grid.addColumn(Customer::getFirstName).setCaption("First name");
		grid.addColumn(Customer::getLastName).setCaption("Last name");

		DataProvider<Customer, String> dataProvider = DataProvider.fromFilteringCallbacks((query) -> customerService
				.getCustomers(filter.getFilter(), query.getOffset(), query.getLimit()).stream(),
				(query) -> customerService.countCustomers(filter.getFilter()));

		grid.setDataProvider(dataProvider);
		return grid;
	}

	@Bean
	@UIScope
	protected MainMenu configureMainMenu() {
		MainMenu menu = new MainMenuBean();

		List<String> menuItemBeans = Arrays.asList(applicationContext.getBeanNamesForAnnotation(MenuItem.class));

		for (String menuItemBean : menuItemBeans) {
			Class<?> beanType = applicationContext.getType(menuItemBean);
			MenuItem menuItemAnnotation = beanType.getAnnotation(MenuItem.class);
			SpringView viewAnnotation = beanType.getAnnotation(SpringView.class);

			menu.addMenuItem(menuItemAnnotation.caption(), menuItemAnnotation.icon(), viewAnnotation.name());
		}

		return menu;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
