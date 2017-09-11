package com.nsd.diamondcard.Config;


import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.DBLayerControllers.DBUserImpl;
import com.nsd.diamondcard.Model.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityFilterConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                //public
                .antMatchers("/","/index","/about","/news","/shops","/regulations","/shares","/promotional_codes","/user_office")
                .permitAll().
                //protected
                antMatchers("/API**").hasAnyRole(UserRoleEnum.ROLE_BUYER.name(),UserRoleEnum.ROLE_CONTR_AGENT.name(),
                UserRoleEnum.ROLE_MEDIATIOR.name(),UserRoleEnum.ROLE_TARGET_BUYER.name());

        ;

        //login




        http.exceptionHandling().accessDeniedPage("/error");


//        http.authorizeRequests().antMatchers("/", "/home").permitAll().antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
//                .permitAll();
//        http.exceptionHandling().accessDeniedPage("/403");
    }
}