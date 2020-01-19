package com.user.security;


import com.user.security.filter.JwtRequestFilter;
import com.user.service.impl.JWTUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    Environment environment;

    @Autowired
    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }


    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter requestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("SecurityConfig.configureGlobal");
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("SecurityConfig.configure");
        http.csrf().disable();
        /*http.authorizeRequests()
                .antMatchers("/user/**")
                .hasIpAddress(this.environment.getProperty("gateway.ip"));*/
        http.authorizeRequests()
                .antMatchers("/user/**", "/info").permitAll();
        if (activeProfile.equals("dev")) {
            http.authorizeRequests()
                    .antMatchers("/actuator/**").permitAll();
        }
        http.authorizeRequests().anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("SecurityConfig.passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.debug("SecurityConfig.authenticationManagerBean");
        return super.authenticationManagerBean();
    }
}
