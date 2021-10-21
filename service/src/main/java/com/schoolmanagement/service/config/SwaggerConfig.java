package com.schoolmanagement.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) //bütün paketleri tarıyor ve tüm controllerları buluyor
                //.apis(RequestHandlerSelectors.basePackage("com.schoolsystem.student.controller")) //controlleradvice'ı göstermedik
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/foos/*")) istersek sadece belirli bir api'yi gösterebiliriz.
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    /**
     * Swagger also provides some default values in its response,
     * which we can customize, such as “Api Documentation”,
     * “Created by Contact Email”, and “Apache 2.0”.
     *
     * To change these values, we can use the apiInfo
     * (ApiInfo apiInfo) method — the ApiInfo class
     * that contains custom information about the API:
     */
    private ApiInfo metaData(){
        Contact contact=new Contact("Seyda Özdemir","http://github.com/tableonthewall","seydaozdemir07@gmail.com")  ;
        return new ApiInfo(
                "School Management",
                "About school informations",
                "1.0",
                "Terms of service: ",
                contact,
                "Apache License Version 2.0",
                "some url",
                new ArrayList<>());
    }

    /**
     * eğer ui arayüz çalışmaz ise aşağıdaki kodu kullanabiliriz
     * @Override
     * public void addResourceHandlers(ResourceHandlerRegistry registry) {
     *     registry.addResourceHandler("swagger-ui.html")
     *       .addResourceLocations("classpath:/META-INF/resources/");
     *
     *     registry.addResourceHandler("/webjars/**")
     *       .addResourceLocations("classpath:/META-INF/resources/webjars/");
     * }
     */
}
