package com.jestarok.practica14.Views;

import com.jestarok.practica14.model.CustomerService;
import com.jestarok.practica14.model.Customer;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
@SpringUI
public class MyUI extends UI {

//    public CustomerRepository repo;// = CustomerService.getInstance();
    public CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid;
    private TextField filter;

    @Autowired
    private CustomerForm form;

    public MyUI(CustomerForm editor){
        this.form = editor;
        grid =  new Grid(Customer.class);
        filter =  new TextField();
        form =  new CustomerForm();

    }
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        filter.setPlaceholder("Filter by name...");
        filter.addValueChangeListener(e -> updateList());
        filter.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilter = new Button(VaadinIcons.CLOSE);
        clearFilter.setDescription("Clear the filter text");
        clearFilter.addClickListener(e -> filter.clear());

        CssLayout filtering = new CssLayout();

        filtering.addComponents(filter,clearFilter);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addCustomer = new Button("Add new customer");
        addCustomer.addClickListener(e->{
            grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });

        HorizontalLayout toolBar = new HorizontalLayout(filtering,addCustomer);

        grid.setColumns("id","firstName","lastName","email","status");
        HorizontalLayout main = new HorizontalLayout(grid,form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid,1);
        layout.addComponents(toolBar,main);

        setContent(layout);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(event ->{
            if(event.getValue() == null){
                form.setVisible(false);
            }else {
                form.setCustomer(event.getValue());
            }
        });

        updateList();
    }

    public void updateList(){
        List<Customer> customers = service.findAll(filter.getValue());
        grid.setItems(customers);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
