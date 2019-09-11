package com.kevinhoutz.controller

import com.kevinhoutz.TestModels
import com.kevinhoutz.RateConfig
import com.kevinhoutz.service.RateService
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(SpringRunner::class)
@WebMvcTest(RatesController::class)
internal class RatesControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var rateService: RateService

    @Before
    fun setUp() {
        `when`(rateService.currentRates()).thenReturn(RateConfig(TestModels.RATES))
    }

    @Test
    fun currentRates() {
        mockMvc.perform(get("/rates/current"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.rates[0].days", `is`("mon,tues,thurs")))
                .andExpect(jsonPath("$.rates[0].price", `is`(1500)))
    }

    @Test
    fun lookupRate_wed() {
        val startDate = LocalDateTime.parse("2015-07-01T07:00:00-05:00", DateTimeFormatter.ISO_DATE_TIME)
        val endDate = LocalDateTime.parse("2015-07-01T12:00:00-05:00", DateTimeFormatter.ISO_DATE_TIME)

        `when`(rateService.getRate(startDate, endDate)).thenReturn(1750)

        mockMvc.perform(get("/rates/lookup")
                .param("startDate", startDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("endDate", endDate.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", `is`(1750)))
    }
}