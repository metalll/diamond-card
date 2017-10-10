package com.nsd.diamondcard.Config;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import com.nsd.diamondcard.Model.UserRoleEnum;
import com.nsd.diamondcard.Security.RESTAuthenticationEntryPoint;
import com.nsd.diamondcard.Security.RESTAuthenticationFailureHandler;
import com.nsd.diamondcard.Security.RESTAuthenticationSuccessHandler;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;

import java.net.URISyntaxException;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityFilterConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public DataSource dataSource() throws URISyntaxException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        DataSource basicDataSource = new DataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                //public
                .antMatchers("/","/auth","/index","/regBuyer","/about","/news","/shops","/regulations","/shares","/promotional_codes","/user_office","/login","/logout","/error")
                .permitAll()
                //protected
                .antMatchers("/API**").hasAnyRole(UserRoleEnum.ROLE_BUYER.name(),UserRoleEnum.ROLE_CONTR_AGENT.name(),
                UserRoleEnum.ROLE_MEDIATIOR.name(),UserRoleEnum.ROLE_TARGET_BUYER.name(),UserRoleEnum.ROLE_ADMIN.name(),UserRoleEnum.ROLE_SUPERADMIN.name())
                //private
                .antMatchers("/admin**").hasAnyRole(UserRoleEnum.ROLE_ADMIN.name(),UserRoleEnum.ROLE_SUPERADMIN.name())
                //and super private
                .antMatchers("/super_admin**").hasAnyRole(UserRoleEnum.ROLE_SUPERADMIN.name())
        ;
        http.exceptionHandling().accessDeniedPage("/error");
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(authenticationFailureHandler);
        http.logout().logoutSuccessUrl("/");

        http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
    }
}