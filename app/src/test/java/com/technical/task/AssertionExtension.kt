package com.technical.task

import org.junit.Assert

fun testAssertEquals(expectedValue: Any, currentValue: Any, methodName: String) {
    println("Test $methodName")
    println("Expected value: $expectedValue Current value: $currentValue")
    Assert.assertEquals(expectedValue, currentValue)
}

fun testAssertFalse(currentValue: Boolean, methodName: String) {
    println("Test $methodName")
    println("Current value: $currentValue")
    Assert.assertFalse(currentValue)
}

fun testAssertTrue(currentValue: Boolean, methodName: String) {
    println("Test $methodName")
    println("Current value: $currentValue")
    Assert.assertTrue(currentValue)
}