package com.fauzan.springboot.springBootFauzan.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/public").permitAll() 
            .antMatchers("/registration**", "/js/**", "/css/**", "/img/**").permitAll()
            .antMatchers("/addProduct").hasRole("ADMIN")
            .anyRequest().authenticated() 
            .and()
            .formLogin()
            .loginPage("/login") 
            .defaultSuccessUrl("/home", true)
            .permitAll() 
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
            .logoutSuccessUrl("/login")
            .and()
            .csrf().disable(); 
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
