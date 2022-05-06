package edu.hm.crm.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuration for Swagger API documentation.
 *
 * @author Simon Hirner
 */
@Configuration
public class SwaggerConfig {

    public static final String CONTACT_TAG = "Contacts";
    public static final String INTERACTION_TAG = "Interactions";
    public static final String OPPORTUNITY_TAG = "Opportunities";

    @Bean
    public Docket contactApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Contacts")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(CONTACT_TAG, "Operations about Contacts."), new Tag(INTERACTION_TAG, "Operations about Interactions."), new Tag(OPPORTUNITY_TAG, "Operations about Opportunities."))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Micro-CRM REST API")
                .description("REST API for Micro-CRM.")
                .version("V1.0")
                .build();
    }

}
