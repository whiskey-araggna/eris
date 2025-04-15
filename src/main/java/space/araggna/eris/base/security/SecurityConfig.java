package space.araggna.eris.base.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import space.araggna.eris.base.domain.entity.UserRole;
import space.araggna.eris.base.ui.view.auth.LoginView;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(
                                new AntPathRequestMatcher(
                                        "/images/**/*.{png,jpg,jpeg,svg}}"
                                )
                        )
                        .permitAll()
        );

        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        var user = User.withUsername("admin@eris.araggna.space")
                .password("{noop}admin123")
                .roles(UserRole.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user);
   }

}
