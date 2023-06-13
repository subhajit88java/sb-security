package com.subhajit.sbsecurity.config;

import com.subhajit.sbsecurity.entrypoint.AuthEntryPointBasic;
import com.subhajit.sbsecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// used to enable @Secured annotation
//@EnableGlobalMethodSecurity(
//        securedEnabled = true)
// used to enable @PreAuthorize annotaion
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthEntryPointBasic unauthorizedHandler;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    // Password encoding bean, raw password will be encoded and will be kept in internal memory or database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() // asking spring security framework to implement authentication and authorization criterias for th euincoming request
                .antMatchers("/get-public-user") // compares the given endpoint with the incoming one
                .permitAll() // in antMatcher returns true then authentication and authorization checks will not be applied
                .antMatchers("/post-public-user")
                .permitAll()
                .antMatchers("/put-public-user")
                .permitAll()
                .antMatchers("/delete-public-user")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) // exemption will be only on OPTIONS and GET method if permit all is applied, for POST/PUT/DELETE it will not work even after permitting it, need to check for PUT and DELETE
                .permitAll()
                .antMatchers("/get-private-user")
                .hasRole("private-user") // Authentication will be applied 1st and then authorization. If the incoming user has the given role then only it can access the given resource
                .antMatchers("/post-private-user")
                .hasRole("private-user")
                .antMatchers("/put-private-user")
                .hasRole("private-user")
                .antMatchers("/delete-private-user")
                .hasRole("private-user")
                .antMatchers("/admin-user")
                .hasRole("admin")
                .anyRequest() // All other requests except those which are mentioned with ant-matchers will be authenticated only, no authorization
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler) // If authentication exception occurs then the given class will be called where error message can we customized
                .and()
                //.formLogin();
                .httpBasic(); // Asking spring security framework to implement Basic Auth. The incoming request will be intercepted by BasicAuthFilter
        return http.build();
    }

}
