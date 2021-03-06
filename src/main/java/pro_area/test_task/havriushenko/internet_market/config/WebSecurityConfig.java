package pro_area.test_task.havriushenko.internet_market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pro_area.test_task.havriushenko.internet_market.security.JWTAuthentificationFilter;
import pro_area.test_task.havriushenko.internet_market.security.JWTAuthorizationFilter;
import pro_area.test_task.havriushenko.internet_market.service.UserService;

import static pro_area.test_task.havriushenko.internet_market.util.SecurityConstants.*;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig() {
    }

    @Bean(name = "bCryptPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                        .csrf().disable().authorizeRequests()
                        .antMatchers(HttpMethod.POST, SING_UP_URL).permitAll()
                   .and()
                         .csrf().disable().authorizeRequests()
                         .antMatchers(HttpMethod.GET, GET_ALL_PRODUCTS_URL).permitAll()
                   .and()
                         .csrf().disable().authorizeRequests()
                         .antMatchers(HttpMethod.GET, GET_PRODUCT_BY_ID_URL).permitAll()
                         .anyRequest().authenticated()
                   .and()
                         .addFilter(new JWTAuthentificationFilter(authenticationManager()))
                         .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

}
