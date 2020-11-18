package HotelBooking.backend.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
@Configuration
public class OAuth2Config extends WebSecurityConfigurerAdapter {

    // ...

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http
        	.headers().frameOptions().sameOrigin()
        	.and()
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST,"/booking").hasAnyRole("ADMIN","MANAGER","USER")
        	.and()
            .logout(l -> l
                    .logoutSuccessUrl("/").permitAll()
            )
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .oauth2Login()
            .defaultSuccessUrl("/oauth2-successed", true);
    }
}