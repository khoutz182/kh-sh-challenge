package com.kevinhoutz.service

import com.kevinhoutz.Rate
import com.kevinhoutz.RateConfig
import com.kevinhoutz.exception.UnavailableException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

// TODO: impl names are bad
@Service
class RateServiceImpl(val rateConfig: RateConfig): RateService {
    override fun getRate(startDate: LocalDateTime, endDate: LocalDateTime): Int {
        return rateConfig.rates
                .filter { rateMatchesDate(it, startDate) }
                .firstOrNull { rateMatchesDate(it, endDate) }
                ?.price ?: throw UnavailableException()
    }

    override fun currentRates(): RateConfig {
        return rateConfig
    }

    override fun setRates(rates: RateConfig) {
        rateConfig.rates = rates.rates
    }

    private fun rateMatchesDate(rate: Rate, date: LocalDateTime): Boolean {
        val zonedDate = date.atZone(ZoneId.of(rate.tz))
        val dowFormatter = DateTimeFormatter.ofPattern("EE")
        val dayOfWeek = zonedDate.format(dowFormatter)
        if (rate.days.split(",")
                .none { day -> dayOfWeek.startsWith(day, ignoreCase = true) }) {
            return false
        }
        val timeFormatter = DateTimeFormatter.ofPattern("HHmm")
        val time = Integer.parseInt(zonedDate.format(timeFormatter))
        val times = rate.times.split("-")
                .map { Integer.parseInt(it) }
        return time in times[0]..times[1]
    }
}