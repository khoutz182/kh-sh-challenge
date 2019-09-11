package com.kevinhoutz

import java.time.LocalDateTime

data class Rate(
        val days: String,
        val times: String,
        val tz: String,
        val price: Int)

open class RateConfig(var rates: Array<Rate>)

class RatePostRequest(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime)