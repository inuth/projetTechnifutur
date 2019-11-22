package be.spring.bootProject.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import be.spring.bootProject.services.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// dans cette mÃ©thode-lÃ , on va dire quel requÃªte nÃ©cessite quel
	// role, etc
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf() // cross site request forgery
                .disable()
                .exceptionHandling()
                .and()
                //.authenticationProvider(getProvider())
                .formLogin()
                //.failureUrl("/unauthorized")
                //.loginPage("/login") // custom login page
                //.loginProcessingUrl("/login") // url to submit the username and password to
                // on peut avoir en + : defaultSuccessUrl()  &  failureUrl
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                // L'ordre des antMatchers est important, il faut aller du plus spécifique au plus général
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/product/details/*").hasRole("MEMBER")
                .antMatchers("/product/delete/*").hasRole("ADMIN")
                .antMatchers("/product/edit/*").hasRole("ADMIN")
                .anyRequest().permitAll();
        // Afin de pouvoir utiliser la console h2 
        http.headers().frameOptions().disable();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	} 
	
	@Override
	protected UserDetailsService userDetailsService() {
		return userService;
	}
}
