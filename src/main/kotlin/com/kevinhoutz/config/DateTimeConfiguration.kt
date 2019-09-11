package com.kevinhoutz.config

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter

/**
 * Provides:
 *  - [Jackson2ObjectMapperBuilderCustomizer] - Customized to deserialize DateTime strings in ISO-8601 format
 *
 * @author khoutz
 */
@Configuration
class DateTimeConfiguration {

    @Bean
    fun customJson(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer {
            it.deserializers(LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
        }
    }
}