package com.vaadin.training.spring.menu;

import com.vaadin.server.Resource;
import com.vaadin.training.spring.IsComponent;
import com.vaadin.ui.VerticalLayout;

public interface MainMenu extends IsComponent<VerticalLayout> {

	MenuItem addMenuItem(String caption, Resource icon, String viewName);

	public interface MenuItem {

	}
}
