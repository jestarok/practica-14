package com.jestarok.practica14.Views;

import com.jestarok.practica14.model.Customer;
import com.jestarok.practica14.model.CustomerRepository;
import com.jestarok.practica14.model.CustomerService;
import com.jestarok.practica14.model.CustomerStatus;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CustomerForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");
    private NativeSelect<CustomerStatus> status = new NativeSelect<>("Status");
    private DateField birthdate = new DateField("Birthday");
    private Button save =  new Button("Save");
    private Button delete =  new Button("Delete");

    private CustomerService service = CustomerService.getInstance();
    private CustomerRepository repo;
    private Customer customer;

    private Binder<Customer> binder = new Binder<>(Customer.class);

    @Autowired
    public CustomerForm(){

//        this.service = myUI.service;
        this.service = CustomerService.getInstance();
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save,delete);
        addComponents(firstName,lastName,email,status,birthdate,buttons);

        status.setItems(CustomerStatus.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        binder.setBean(customer);

        delete.setVisible(customer.getId() != null);
        setVisible(true);
        firstName.selectAll();
    }

    private void delete(){
        service.delete(customer);
//        myUI.updateList();
        setVisible(false);
    }
    private void save(){
        service.save(customer);
//        myUI.updateList();
        setVisible(false);
    }
}
