package com.kevinhoutz.controller

import com.kevinhoutz.RateConfig
import com.kevinhoutz.RatePostRequest
import com.kevinhoutz.service.RateService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * Provides access to the rates config as well as lookups in to the rates available for certain datetime ranges.
 *
 * Apis provided:
 *  - `/rates` - PUT - Sets the rate configuration object
 *  - `/rates/current` - GET - Returns the current rate configuration object loaded in memory
 *  - `/rates/lookup` - GET/POST - Returns the available rate for a specified datetime range, otherwise "unavailable"
 *
 * @author khoutz
 */
@RestController
@RequestMapping("/rates")
class RatesController(private val rateService: RateService) {

    @ApiOperation("Set the rates according to the attached json payload")
    @PutMapping
    fun setRates(@RequestBody config: RateConfig) {
        rateService.setRates(config)
    }

    @ApiOperation("Get the current rate configuration as json")
    @GetMapping("/current")
    fun currentRates(): RateConfig {
        return rateService.currentRates()
    }

    @ApiOperation("Look up a rate for a specified datetime range")
    @GetMapping("/lookup")
    fun lookupRate(@RequestParam
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                   @ApiParam(required = true, name = "Start Date", example = "2015-07-01T07:00:00-05:00")
                   startDate: LocalDateTime,
                   @RequestParam
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                   @ApiParam(required = true, name = "End Date", example = "2015-07-01T12:00:00-05:00")
                   endDate: LocalDateTime): Int {
        return rateService.getRate(startDate, endDate)
    }

    @PostMapping("/lookup")
    fun lookupRate(@RequestBody body: RatePostRequest): Int {
        return rateService.getRate(body.startDate, body.endDate)
    }
}