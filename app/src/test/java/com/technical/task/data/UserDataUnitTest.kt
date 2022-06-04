package com.technical.task.data

import com.technical.task.data.model.UserListState
import com.technical.task.domain.repository.UserListRepository
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
import java.util.*

class UserDataUnitTest {

    @get:Rule
    var testCoroutineRule = CoroutineRule()

    @RelaxedMockK
    private lateinit var userListRepository: UserListRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `testUserRepositoryWithSuccessState`() {
        val userList = LinkedList<UserModel>().apply {
            add(UserModel(id = 0, name = "John", email = "john@john.com",
                gender = "male", status = "active"))
            add(UserModel(id = 0, name = "John", email = "john@john.com",
                gender = "male", status = "active"))
        }
        val expectedValue = flowOf(UserListState.UserListDownloadSuccess(userList))
        coEvery { userListRepository.downloadUserList() } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = userListRepository.downloadUserList()
            assertThat(call, equalTo(expectedValue))
        }
    }

    @Test
    fun `testUserRepositoryWithFailureState`() {
        val expectedValue = flowOf(UserListState.UserListDownloadFailure)
        coEvery { userListRepository.downloadUserList() } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = userListRepository.downloadUserList()
            assertThat(call, equalTo(expectedValue))
        }
    }

    @Test
    fun `testUserRepositoryWithEmptyState`() {
        val expectedValue = flowOf(UserListState.UserListIsEmpty)
        coEvery { userListRepository.downloadUserList() } answers {
            expectedValue
        }
        testCoroutineRule.runBlockingTest {
            val call = userListRepository.downloadUserList()
            assertThat(call, equalTo(expectedValue))
        }
    }
}