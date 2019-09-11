package com.kevinhoutz.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kevinhoutz.RateConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class MiniSpotHeroConfiguration(@Value("classpath:rates.json") val ratesResource: Resource) {

    @Bean
    fun currentRates(objectMapper: ObjectMapper): RateConfig {
        val initialRates = objectMapper.readValue<RateConfig>(ratesResource.inputStream)
        LOG.info("Initial rates: $initialRates")
        return initialRates
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
    }
}