/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.footer.Footer;
import zm.hashcode.mshengu.client.web.header.Header;
import zm.hashcode.mshengu.client.web.siderbar.Sidebar;

/**
 *
 * @author boniface
 */
@PreserveOnRefresh
@Theme("dashboard")
public class MshenguMain extends UI {

    public final HorizontalSplitPanel content = new HorizontalSplitPanel();
    public final Footer footer = new Footer();
    private static ThreadLocal<MshenguMain> threadLocal = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;
    private SpringContext ctx;
    private Authentication auth;

    @Override
    protected void init(VaadinRequest request) {
        setInstance(this);
        setContent(new LoginWindow(this));
    }

    public static MshenguMain getInstance() {
        return threadLocal.get();
    }

    // Set the current application instance
    public static void setInstance(MshenguMain application) {
        if (getInstance() == null) {
            threadLocal.set(application);
        }
    }

    public Authentication authenticate(String login, String password) throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        ctx = new SpringContext();
        authenticationManager = (AuthenticationManager) ctx.getStringBean("authenticationManager");
        setAuth(authenticationManager.authenticate(token));
        if (getAuth() != null) {
            SecurityContextHolder.getContext().setAuthentication(getAuth());
            MshenguMain.setInstance(this);
            loadProtectedResources();
            return getAuth();
        }
        throw new Exception("failed to Login");
    }

    public Authentication getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(Authentication auth) {
        this.auth = auth;
    }

    private void loadProtectedResources() {
        final Header header = new Header();
        content.setMaxSplitPosition(20, Unit.PERCENTAGE);
        content.setHeight("1000px");

        content.setLocked(true);
        content.setFirstComponent(new Sidebar(this));

        content.setSecondComponent(new KPIMenu(this, "LANDING"));
        final VerticalLayout root = new VerticalLayout();
        root.addStyleName("main-view");
        root.setMargin(new MarginInfo(false, true, false, true));
        root.addComponent(header);
        root.addComponent(content);
        root.addComponent(footer);
        setContent(root);
    }
}
