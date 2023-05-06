package com.bdran.InventorySystem.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//               .antMatchers("/user/**").hasRole("user").
//                antMatchers("role/**").hasRole("admin").
//                anyRequest().authenticated();
//    }
@Override
protected void configure(HttpSecurity http) throws Exception {
            http
            .csrf().disable() // disable CSRF protection
            .authorizeRequests()
//            .antMatchers("/api/v1/role/**").hasAuthority("admin") // allow access to /api/v1/role for users with ADMIN role
//            .antMatchers("/api/v1/user/**").hasAuthority("user") // allow access to /api/v1/user for users with USER role
            .anyRequest().authenticated().and().formLogin().loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout").permitAll() //all other requests require authentication
            .and()
            .httpBasic(); // use HTTP Basic authentication
}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}123").authorities("ADMIN")
//                .and().withUser("user").password("{noop}123").authorities("USER");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }



}
