package HotelBooking.backend.security;



import javax.sql.DataSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AdminConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http
        	.headers().frameOptions().sameOrigin()
        	.and()
        	.csrf().disable()
        	.antMatcher("/admin/**")
        	.authorizeRequests()
        	.and()
            .logout(l -> l
                    .logoutSuccessUrl("/admin").permitAll()
            )
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .formLogin()
            .failureUrl("/admin?error=true")
            .loginProcessingUrl("/admin/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/admin/dashboard", true);
          ;
    }
	
	 @Autowired
	    public void initialize(AuthenticationManagerBuilder builder) throws Exception {
//	    	builder.authenticationProvider(authProvider);
		 builder.jdbcAuthentication().dataSource(dataSource).rolePrefix("ROLE_")
			.usersByUsernameQuery(
				"select username,password,enable from account where username=?")
			.authoritiesByUsernameQuery(
				"select username,role from account where username=?")
			.passwordEncoder(passwordEncoder());
//	    	builder.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
	    }
	 
	 @Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	   return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 }
	 
	
	 
	 
}
