package application.model;

import application.service.FelhasznaloDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public FelhasznaloDetailsService userDetailsService() {
        return new FelhasznaloDetailsService();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return provider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/profil", "fizetes/*", "etterem/*", "/kosar").authenticated()
                .antMatchers("/termek").hasAnyAuthority("ETTEREM")
                .antMatchers("/admin", "/profil/*").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/home")
                .and()
                .logout().logoutSuccessUrl("/");
    }
}
