package com.bala.springbootsecurity.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly=true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    //@Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
         
     
    	 http
         .authorizeRequests()
             .antMatchers("/resources/**", "/register").permitAll()
             .anyRequest().authenticated()
             .and()
             .formLogin().defaultSuccessUrl("/welcome", true)
             .loginPage("/login")
             .permitAll()
             .and()
             .logout().logoutSuccessUrl("/login")
             .permitAll();
       
    }
    
    @Override
    public void configure(final WebSecurity web) throws Exception {
       // web.ignoring().antMatchers("/WEB-INF/jsp/**");
        //web.ignoring().antMatchers("/resources/**");
    	web
        .ignoring()
           .antMatchers("/resources/**");
        
    }*/

   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }  
/*
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("xyz@gmail.com").password("password")
                .roles("USER", "ADMIN");
    } */
}