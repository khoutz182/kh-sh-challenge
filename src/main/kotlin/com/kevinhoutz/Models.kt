package com.kevinhoutz

import java.time.LocalDateTime

data class Rate(
        val days: String,
        val times: String,
        val tz: String,
        val price: Int)

/**
 * Array of [Rate]s and provided as a singleton bean for access in the services and controllers. Set as `open` to let
 * mockito mock it out.
 */
open class RateConfig(var rates: Array<Rate>)

class RatePostRequest(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime)
