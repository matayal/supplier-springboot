package springboot.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class SupplierSpringbootApplication extends SpringBootServletInitializer{

    	public static void main(String[] args) {
    		SpringApplication.run(SupplierSpringbootApplication.class, args);
    	}
    	
    	@Override
    	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
    		return builder.sources(SupplierSpringbootApplication.class);
    	}
    	
}