package com.kevinhoutz

class Models {
    companion object {
        val RATES = arrayOf(
                Rate(days = "mon,tues,thurs", times = "0900-2100", tz = "America/Chicago", price = 1500),
                Rate(days = "fri,sat,sun", times = "0900-2100", tz = "America/Chicago", price = 2000),
                Rate(days = "wed", times = "0600-1800", tz = "America/Chicago", price = 1750),
                Rate(days = "mon,wed,sat", times = "0100-0500", tz = "America/Chicago", price = 1000),
                Rate(days = "sun,tues", times = "0100-0700", tz = "America/Chicago", price = 925)
        )
    }
}