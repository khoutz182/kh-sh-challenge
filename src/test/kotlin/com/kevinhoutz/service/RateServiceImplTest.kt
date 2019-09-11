package com.kevinhoutz.service

import com.kevinhoutz.TestModels
import com.kevinhoutz.RateConfig
import com.kevinhoutz.exception.UnavailableException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class RateServiceImplTest {

    private lateinit var rateService: RateServiceImpl

    @BeforeEach
    internal fun setUp() {
        rateService = RateServiceImpl(RateConfig(rates = TestModels.RATES))
    }

    @Test
    fun getRateWed_valid() {
        val startDateTime = LocalDateTime.parse("2015-07-01T07:00:00-05:00", DateTimeFormatter.ISO_DATE_TIME)
        val endDateTime = LocalDateTime.parse("2015-07-01T12:00:00-05:00", DateTimeFormatter.ISO_DATE_TIME)
        val rate = rateService.getRate(startDate = startDateTime, endDate = endDateTime)
        Assertions.assertEquals(1750, rate)
    }

    @Test
    fun getRateSat_valid() {
        val startDateTime = LocalDateTime.parse("2015-07-04T15:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME)
        val endDateTime = LocalDateTime.parse("2015-07-04T20:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME)
        val rate = rateService.getRate(startDate = startDateTime, endDate = endDateTime)
        Assertions.assertEquals(2000, rate)
    }

    @Test
    fun getRateSat_unavailable() {
        val startDateTime = LocalDateTime.parse("2015-07-04T07:00:00+05:00", DateTimeFormatter.ISO_DATE_TIME)
        val endDateTime = LocalDateTime.parse("2015-07-04T20:00:00+05:00", DateTimeFormatter.ISO_DATE_TIME)
        Assertions.assertThrows(UnavailableException::class.java) {
            rateService.getRate(startDate = startDateTime, endDate = endDateTime)
        }
    }

    @Test
    fun getRateMultipleDays_unavailable() {
        val startDateTime = LocalDateTime.parse("2015-07-01T07:00:00-05:00", DateTimeFormatter.ISO_DATE_TIME)
        val endDateTime = LocalDateTime.parse("2015-07-04T20:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME)
        Assertions.assertThrows(UnavailableException::class.java) {
            rateService.getRate(startDate = startDateTime, endDate = endDateTime)
        }
    }
}