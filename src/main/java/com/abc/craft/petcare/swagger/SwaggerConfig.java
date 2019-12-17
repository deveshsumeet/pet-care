package com.abc.craft.petcare.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The SwaggerConfig class maintains all the swagger configs and denotes the
 * base package of controller class to build api doc.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Method to handle api informations.
	 * 
	 * @param none.
	 * @return Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.abc.craft.petcare.controllers"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
	}

	/**
	 * Method to handle and show api information.
	 * 
	 * @param none.
	 * @return ApiInfo
	 */
	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Pet Care Demo Project API")
				.description("Pet Care Demo Project API Reference for Developers").termsOfServiceUrl("TOC")
				.contact(new Contact("Devesh Sumeet", "", "deveshsumeet@gmail.com")).license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
	}

}