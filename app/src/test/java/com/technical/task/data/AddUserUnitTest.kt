package com.technical.task.data

import com.technical.task.data.model.AddUserState
import com.technical.task.domain.repository.AddUserRepository
import com.technical.task.presentation.list.model.UserModel
import com.technical.task.rule.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddUserUnitTest {

    @get:Rule
    var testCoroutineRule = CoroutineRule()

    @RelaxedMockK
    private lateinit var addUserRepository: AddUserRepository

    private val user = UserModel(
        id = 0,
        name = "John",
        email = "john@john.com",
        gender = "male",
        status = "active")

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `testAddUserRepositoryWithSuccessState`() {
        val expectedValue = flowOf(AddUserState.AddUserSuccess)
        coEvery { addUserRepository.addUser(user) } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = addUserRepository.addUser(user)
            assertThat(call, equalTo(expectedValue))
        }
    }

    @Test
    fun `testAddUserRepositoryWithFailureState`() {
        val expectedValue = flowOf(AddUserState.AddUserFailure)
        coEvery { addUserRepository.addUser(user) } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = addUserRepository.addUser(user)
            assertThat(call, equalTo(expectedValue))
        }
    }
}