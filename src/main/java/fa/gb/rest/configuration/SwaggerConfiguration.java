package fa.gb.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	private static final ApiInfo apiInfo;
	static {
		String title = "Graham Baitson - FA";
		String description = "A REST API for the Graham Baitson - FA project.";
		String version = "1.0";
		String termsOfServiceUrl = "";
		Contact contact = new Contact("Graham Baitson", "", "grahambaitson@gmail.com");
		String license = "";
		String licenseUrl = "";
		apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(Predicates.not(PathSelectors.regex("/error"))) // Exclude Spring error controllers                
          .build()
          .apiInfo(apiInfo);
    }
}
