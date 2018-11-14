package com.example.kotlinarchsample

import com.example.kotlinarchsample.profile.NumberToStringInteractor
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InteractorsTest {

    private lateinit var numberToStringInteractor: NumberToStringInteractor

    @Before
    fun before() {
        numberToStringInteractor = NumberToStringInteractor()
    }

    @Test
    fun testNumberInteractor() = runBlocking {
        val result = numberToStringInteractor(625)
        assert(result == "Number is 625")
    }
}