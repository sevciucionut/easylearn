package com.learning.easylearn.configuration;

import com.learning.easylearn.CORSFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import java.util.Optional;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.POST,"/course/post").permitAll()
                .antMatchers(HttpMethod.POST,"/school/create").permitAll()
                .antMatchers(HttpMethod.PUT,"/school/admin").permitAll()
                .antMatchers(HttpMethod.PUT,"/student/course").permitAll()
                .antMatchers(HttpMethod.PUT,"/student/school").permitAll()
                .antMatchers(HttpMethod.PUT,"/teacher/school").permitAll()
                .antMatchers(HttpMethod.PUT,"/teacher/course").permitAll()
                .antMatchers(HttpMethod.GET,"/school/all").permitAll()
                .antMatchers(HttpMethod.GET,"/login").permitAll()
                .antMatchers(HttpMethod.GET,"/course/all").permitAll()
                .antMatchers(HttpMethod.DELETE,"/course/delete").permitAll()
                .antMatchers(HttpMethod.DELETE,"/student/delete").permitAll()
                .antMatchers(HttpMethod.DELETE,"/teacher/delete").permitAll()
                .antMatchers(HttpMethod.DELETE,"/course/delete").permitAll()
                .antMatchers(HttpMethod.DELETE,"/schooladmin/delete").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
                .cors().and().csrf().disable().authorizeRequests()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}