package com.kevinhoutz.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis { true }
                .paths { true }
                .build()
    }
}