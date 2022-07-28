package com.neylandev.delivery.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

    @Value("${project.version}")
    private String version;

    @Value("${springfox.documentation.swagger.v2.contactEmail}")
    private String email;

    @Value("${springfox.documentation.swagger.v2.contactName}")
    private String name;

    @Value("${springfox.documentation.swagger.v2.contactUrl}")
    private String url;

    @Value("${springfox.documentation.swagger.v2.home}")
    private String home;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neylandev.delivery.application.controller"))
                .paths(PathSelectors.any())
                .build().pathMapping("/").apiInfo(apiInfo());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(home + "/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        final String title = "Delivery API Rest";
        return new ApiInfoBuilder().title(title)
                .description("Documentação da ".concat(title))
                .version(version)
                .contact(new Contact(name, url, email))
                .build();
    }
}
