package com.blogapplication.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	//read the values from to the applications.properties file 
	@Value("${name}")
	public String projectName;

	
	// created the security Configurations to the swagger 
	public static final String AUTHORIZATION_HEADER="Authorizaiton";
	
	private ApiKey apiKey() {
		
		return new ApiKey("JWT", AUTHORIZATION_HEADER,"header" );
	}
	
	private List<SecurityContext> securityContexts(){
		
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
	}
	
	private List<SecurityReference> securityReferences(){
		
		AuthorizationScope scopes=new AuthorizationScope("globle", "accessEvarything");
		
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{scopes}));
	}
	
	
	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
				
				
				.apiInfo(new ApiInfo(this.projectName, "learn from basic", "v2",
						"krn@123gmail.com", new Contact("KARAN", "krn@134gmail.com", "krn@123gmail.com"), "public",
						"localhost:8080", Collections.EMPTY_LIST))	
				
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select().apis(RequestHandlerSelectors.basePackage("com.blogapplication")).paths(PathSelectors.any())
				.build();

	}


	
}
