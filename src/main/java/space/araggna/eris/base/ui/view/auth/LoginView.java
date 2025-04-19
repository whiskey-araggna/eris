package space.araggna.eris.base.ui.view.auth;

import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "login", autoLayout = false)
@PageTitle("Login")
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    public LoginView() {
        setAction("login");
        setOpened(true);
        setForgotPasswordButtonVisible(true);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            setError(true);
        }
    }
}
