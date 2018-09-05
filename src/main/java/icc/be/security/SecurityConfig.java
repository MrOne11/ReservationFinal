package icc.be.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
		.formLogin().loginPage("/login")
			.permitAll()
				.and()
				.httpBasic()
					.and()
				.csrf().disable()
				.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login?logout")
					.permitAll()
				.and()
					.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
					
		//attribuer les résources entre ROLE ADMIN, USER  et les utilisateurs qui ne sont pas 
		//athentifié 
		http.authorizeRequests().antMatchers("/location/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/type/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/locality/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/artiste/form").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/representation/form").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/artiste/edite").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/artiste/index").permitAll();
		http.authorizeRequests().antMatchers("/reservation/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/artiste/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/representation/**").hasAnyAuthority("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().antMatchers("/representation/form").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/representation/edit").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/show/edit").hasAuthority("ADMIN");


		//http.authorizeRequests().antMatchers("/reservation/form").hasAuthority("USER");
		

		
		
		
		


	}
}
