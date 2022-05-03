package com.projectX.configs.security;

import com.projectX.dto.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/api/v1/tech_section").hasAnyRole(Role.ADMIN.name(), Role.DEVELOPER.name())
                .antMatchers("/api/v1/tech_section/api_documentation").hasRole(Role.DEVELOPER.name())
                .antMatchers("/api/v1/tech_section/admin_panel").hasRole(Role.ADMIN.name())
                .antMatchers("/api/v1/user/login", "/api/v1/user/registration").permitAll()
                .antMatchers("/api/v1/user/profile/**").authenticated()

                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
