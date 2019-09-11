package com.kevinhoutz.controller

import com.kevinhoutz.RateConfig
import com.kevinhoutz.RatePostRequest
import com.kevinhoutz.service.RateService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/rates")
class RatesController(private val rateService: RateService) {

    @PutMapping
    fun setRates(@RequestBody config: RateConfig) {
        rateService.setRates(config)
    }

    @GetMapping("/current")
    fun currentRates(): RateConfig {
        return rateService.currentRates()
    }

    @GetMapping("/lookup")
    fun lookupRate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: LocalDateTime,
                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: LocalDateTime): Int {
        return rateService.getRate(startDate, endDate)
    }

    @PostMapping("/lookup")
    fun lookupRate(@RequestBody body: RatePostRequest): Int {
        return rateService.getRate(body.startDate, body.endDate)
    }
}