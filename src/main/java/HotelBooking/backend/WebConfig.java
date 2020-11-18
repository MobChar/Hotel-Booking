package HotelBooking.backend;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/image/**")
          .addResourceLocations("classpath:/images/public/");	
        registry
        .addResourceHandler("/**")
        .addResourceLocations("classpath:/static/");	
    }
}
