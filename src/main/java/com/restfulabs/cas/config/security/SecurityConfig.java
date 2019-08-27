package com.restfulabs.cas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.restfulabs.cas.config.security.jwt.JWTAuthEntryPoint;
import com.restfulabs.cas.config.security.jwt.JWTAuthTokenFilter;
import com.restfulabs.cas.config.security.jwt.JWTProvider;
import com.restfulabs.cas.service.impl.UserDetailsServiceImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;

	private JWTAuthEntryPoint unauthorizedHandler;

	private JWTProvider tokenProvider;

	@Autowired
	public SecurityConfig(UserDetailsServiceImpl userDetailsService, JWTAuthEntryPoint unautuhorizedHandler,
			JWTProvider tokenProvider) {
		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unautuhorizedHandler;
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public JWTAuthTokenFilter authenticationTokenFilter() {
		return new JWTAuthTokenFilter(tokenProvider, userDetailsService);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/authenticate").permitAll()
				.antMatchers("/api/signup").permitAll()
				.antMatchers("/api/admin/**").hasRole("ADMIN").antMatchers("/api/user/**").hasRole("USER").anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
