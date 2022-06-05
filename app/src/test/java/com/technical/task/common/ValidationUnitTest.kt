package com.technical.task.common

import com.technical.task.testAssertFalse
import com.technical.task.testAssertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

class ValidationUnitTest {

    @get: Rule
    var testName = TestName()

    @Test
    fun `testCorrectFieldLength`() {
        val name = "tester"
        val currentValue = name.validateMinimumLength()
        testAssertTrue(currentValue, testName.methodName)
    }

    @Test
    fun `testIncorrectFieldLength`() {
        val name = "test"
        val currentValue = name.validateMinimumLength()
        testAssertFalse(currentValue, testName.methodName)
    }

    @Test
    fun `testCorrectEmailFormat`() {
        val email = "tester@tester.com"
        val currentValue = email.validateEmailPattern()
        testAssertTrue(currentValue, testName.methodName)
    }

    @Test
    fun `testIncorrectEmailFormatWithoutAt`() {
        val email = "testertester.com"
        val currentValue = email.validateEmailPattern()
        testAssertFalse(currentValue, testName.methodName)
    }

    @Test
    fun `testIncorrectEmailFormatWithoutDot`() {
        val email = "tester@testercom"
        val currentValue = email.validateEmailPattern()
        testAssertFalse(currentValue, testName.methodName)
    }

    @Test
    fun `testIncorrectEmailFormatWithoutAtAndDot`() {
        val email = "testertestercom"
        val currentValue = email.validateEmailPattern()
        testAssertFalse(currentValue, testName.methodName)
    }

    @Test
    fun `testIncorrectEmailFormatWithEmptyValue`() {
        val email = ""
        val currentValue = email.validateEmailPattern()
        testAssertFalse(currentValue, testName.methodName)
    }
}