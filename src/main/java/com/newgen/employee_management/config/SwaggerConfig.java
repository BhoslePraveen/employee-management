package com.newgen.employee_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiInformation() {
        Server s1 = new Server()
                .url("http://localhost:8080")
                .description("Local Host Server");
        Server s2 = new Server()
                .url("https://velocity.employee-management-dev.net")
                .description("Dev Server");

        Contact contact = new Contact()
                .name("Praveen Bhosle")
                .email("Praveen.bhosle5@gmail.com");

        Info info = new Info()
                .title("Employee Management")
                .version("1.0")
                .contact(contact)
                .summary("Employee Management Software Development Application")
                .termsOfService("Fair usage only")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        return new OpenAPI().info(info).addServersItem(s1).addServersItem(s2);
    }
}
