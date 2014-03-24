/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.header;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import zm.hashcode.mshengu.app.security.GetUserCredentials;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class Header extends VerticalLayout implements Button.ClickListener {

    private final Button button = new Button("Logout");
    private final HorizontalLayout bannerLayout = new HorizontalLayout();
    private final HorizontalLayout menuLayout = new HorizontalLayout();
    private final Embedded logo = new Embedded(null, new ThemeResource("header.PNG"));
//    private Embedded banner = new Embedded("", new ThemeResource("banner.png"));

    public Header() {

        bannerLayout.setSizeFull();
        menuLayout.setSizeFull();

        Person user = new GetUserCredentials().getLoggedInPerson();

        setSizeFull();
        button.setStyleName(Reindeer.BUTTON_LINK);
        final Label loggedInUser = new Label(" Welcome: " + user.getFirstname() + " " + user.getLastname());
        final Label line = new Label("<HR height=\"50% color=\"#0072BB\" />", ContentMode.HTML);
        logo.setSizeFull();

        bannerLayout.addComponent(logo);
        bannerLayout.setComponentAlignment(logo, Alignment.TOP_LEFT);
//        bannerLayout.addComponent(banner);
//        bannerLayout.setComponentAlignment(banner, Alignment.TOP_LEFT);

        menuLayout.addComponent(loggedInUser);
        menuLayout.setComponentAlignment(loggedInUser, Alignment.TOP_LEFT);

        menuLayout.addComponent(button);
        menuLayout.setComponentAlignment(button, Alignment.TOP_RIGHT);




//        addStyleName(Reindeer.LAYOUT_BLACK);
        button.addClickListener(this);
        addComponent(bannerLayout);

        addComponent(menuLayout);
        addComponent(line);

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        getUI().getPage().setLocation("/mshengu/app/");
        getSession().close();
    }
}
