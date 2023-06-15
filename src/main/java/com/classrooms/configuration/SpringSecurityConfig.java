package com.classrooms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfiguration{
	
	
	
	//@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//auth.inMemoryAuthentication().withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER").and()
		auth.inMemoryAuthentication()
		.withUser("springuser")
		.password(passwordEncoder().encode("spring123"))
		.roles("USER")
		.and()
		.withUser("springadmin")
		.password(passwordEncoder().encode("admin123"))
		.roles("ADMIN","USER");
	}
	
	//@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		/*http.authorizeHttpRequests()
		.requestMatchers("/admin").hasRole("ADMIN")
		.requestMatchers("/user").hasRole("USER")
		.anyRequest()
		.authenticated()
		.and().formLogin().and().oauth2Login();*/
		
		
		http.authorizeHttpRequests(
				(requests->{
					try {
						requests
								.requestMatchers(new AntPathRequestMatcher("/admin"))
								.permitAll()
								.anyRequest().authenticated()
								.and().formLogin().and().oauth2Login();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
