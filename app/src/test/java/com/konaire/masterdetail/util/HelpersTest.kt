package com.konaire.masterdetail.util

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Evgeny Eliseyev on 13/07/2018.
 */
class HelpersTest {
    @Test
    fun doesApiGiveCorrectDateForShortFormat() {
        assertEquals("23/04/2018", "20180423".formatAsDate(false))
    }

    @Test
    fun doesApiGiveCorrectDateForFullFormat() {
        assertEquals("23/04/2018", "2018-04-23T09:53:30Z".formatAsDate(true))
    }

    @Test
    fun doesTimezoneMatter() {
        assertEquals("24/04/2018", "2018-04-23T23:53:30Z".formatAsDate(true))
    }

    @Test
    fun doesAnotherGeneralFormattedDateWork() {
        assertEquals("23/04/2018", "2018-04-23T22:53:30GMT+02:00".formatAsDate(true))
    }
}