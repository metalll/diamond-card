package com.nsd.diamondcard.Config;


import com.nsd.diamondcard.Model.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
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
                .antMatchers("/","/index","/regBuyer","/about","/news","/shops","/regulations","/shares","/promotional_codes","/user_office","/login","/logout","/error")
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

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                // указываем action с формы логина
                .loginProcessingUrl("/j_spring_security_check")
                // указываем URL при неудачном логине
                .failureUrl("/login?error")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutUrl("/logout")
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);
    }
}