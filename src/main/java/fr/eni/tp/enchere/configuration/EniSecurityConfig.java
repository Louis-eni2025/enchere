package fr.eni.tp.enchere.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class EniSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select email, mot_de_passe, 1 from utilisateurs where email=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select email, CASE WHEN administrateur = 1 THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' END from utilisateurs where email=?");
        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth -> {
                    auth
                            .requestMatchers(HttpMethod.GET, "/addArticle").authenticated()
                            .requestMatchers(HttpMethod.POST, "/addArticle").authenticated()
                            .requestMatchers(HttpMethod.GET, "/modifierProfile/*").authenticated()
                            .requestMatchers(HttpMethod.POST, "/modifierProfile/*").authenticated()
                            .requestMatchers(HttpMethod.GET, "/profile/*").authenticated()

                            .requestMatchers(HttpMethod.GET, "/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/.well-known/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/inscription/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/login/*").permitAll()



                            .requestMatchers("/css/*").permitAll()
                            .requestMatchers("/images/*").permitAll()
                            .anyRequest().denyAll();
                }
        );

        // Accès à l'authentification de son utilisateur
        http.formLogin(form ->{
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/");
        });

        /*http.formLogin(withDefaults());*/

        http.logout(logout ->{
            logout
                    .logoutRequestMatcher(request ->
                            request.getMethod().equals("GET") &&request.getRequestURI().endsWith("/logout")
                    )
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .permitAll();
        });
        return http.build();
    }
}