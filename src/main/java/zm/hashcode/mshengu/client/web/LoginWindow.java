/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class LoginWindow extends VerticalLayout {

    private Button btnLogin = new Button("Login");
    private TextField login = new TextField("Username:");
    private PasswordField password = new PasswordField("Password:");
    private final CssLayout loginPanel = new CssLayout();
    private final MshenguMain main;

    public LoginWindow(MshenguMain app) {
        addStyleName("login-layout");
        setMargin(true);
        setSizeFull();
        main = app;
        setCaption("login");
        initUI();
    }

    private void initUI() {
        loginPanel.addStyleName("login-panel");
        loginPanel.setWidth("800px");
        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Welcome to THE MSHENGU");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

        Label title = new Label("Knowledge Management Information System");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");


        login.focus();
        fields.addComponent(login);


        fields.addComponent(password);

        btnLogin.addStyleName("default");
        fields.addComponent(btnLogin);
        fields.setComponentAlignment(btnLogin, Alignment.BOTTOM_LEFT);

        btnLogin.addStyleName("default");

        loginPanel.addComponent(fields);


        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);



        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {

                    main.authenticate((String) login.getValue(), (String) password.getValue());
//                    open(new ExternalResource(HashWorkMain.getInstance().getURL()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Notification.show("Login failed", "Bad Username or Password", Notification.Type.ERROR_MESSAGE);
                }
            }
        });



    }
}
