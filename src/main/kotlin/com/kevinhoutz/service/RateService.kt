package com.kevinhoutz.service

import com.kevinhoutz.RateConfig
import java.time.LocalDateTime

/**
 * Service used to set, lookup, and calculate rates.
 */
interface RateService {

    fun getRate(startDate: LocalDateTime, endDate: LocalDateTime): Int

    fun currentRates(): RateConfig

    fun setRates(rates: RateConfig)
}