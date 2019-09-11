package com.kevinhoutz.config

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter


@Configuration
class DateTimeConfiguration {

    private val dateFormat = "yyyy-MM-dd"
    private val dateTimeFormat = "yyyy-MM-dd HH:mm:ss"

    @Bean
    fun customJson(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer {
            it.deserializers(LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
        }
    }
}