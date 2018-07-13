package com.konaire.teamwork.util

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Evgeny Eliseyev on 13/07/2018.
 */
class HelpersTest {
    @Test
    fun isApiGivenCorrectDate() {
        assertEquals("23.04.2018", "2018-04-23T09:53:30Z".formatAsDate())
    }

    @Test
    fun isTimezoneMattered() {
        assertEquals("24.04.2018", "2018-04-23T23:53:30Z".formatAsDate())
    }

    @Test
    fun isAnotherGeneralFormattedDateWorked() {
        assertEquals("23.04.2018", "2018-04-23T22:53:30GMT+02:00".formatAsDate())
    }
}