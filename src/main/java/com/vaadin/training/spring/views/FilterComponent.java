package com.vaadin.training.spring.views;

import com.vaadin.training.spring.IsComponent;
import com.vaadin.ui.VerticalLayout;

public interface FilterComponent extends IsComponent<VerticalLayout> {

	String getFilter();

	public class FilterEvent {
	}
}
