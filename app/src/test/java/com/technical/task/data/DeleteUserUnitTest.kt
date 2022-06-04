package com.technical.task.data

import com.technical.task.data.model.DeleteUserState
import com.technical.task.domain.repository.DeleteUserRepository
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

class DeleteUserUnitTest {

    @get:Rule
    var testCoroutineRule = CoroutineRule()

    @RelaxedMockK
    private lateinit var deleteUserRepository: DeleteUserRepository

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
    fun `testDeleteUserRepositoryWithSuccessState`() {
        val expectedValue = flowOf(DeleteUserState.DeleteUserSuccess)
        coEvery { deleteUserRepository.deleteUser(user.id) } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = deleteUserRepository.deleteUser(user.id)
            assertThat(call, equalTo(expectedValue))
        }
    }

    @Test
    fun `testDeleteUserRepositoryWithFailureState`() {
        val expectedValue = flowOf(DeleteUserState.DeleteUserFailure)
        coEvery { deleteUserRepository.deleteUser(user.id) } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = deleteUserRepository.deleteUser(user.id)
            assertThat(call, equalTo(expectedValue))
        }
    }
}