package com.technical.task.di

import com.technical.task.data.repository.AddUserRepositoryImpl
import com.technical.task.data.repository.DeleteUserRepositoryImpl
import com.technical.task.data.repository.UserListRepositoryImpl
import com.technical.task.data.service.common.GoRestService
import com.technical.task.data.service.common.NetworkController
import com.technical.task.data.service.mapper.AddUserMapper
import com.technical.task.data.service.mapper.UserListMapper
import com.technical.task.domain.usecase.AddUserUseCase
import com.technical.task.domain.usecase.DeleteUserUseCase
import com.technical.task.domain.usecase.GetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoRestProvider {

    @Provides
    @Singleton
    fun provideUserListRepository(
        goRestService: GoRestService,
        networkController: NetworkController,
        userListMapper: UserListMapper
    ) = UserListRepositoryImpl(goRestService, networkController, userListMapper)

    @Provides
    @Singleton
    fun provideAddUserRepository(
        goRestService: GoRestService,
        networkController: NetworkController,
        addUserMapper: AddUserMapper
    ) = AddUserRepositoryImpl(goRestService, networkController, addUserMapper)

    @Provides
    @Singleton
    fun provideDeleteUserRepository(
        goRestService: GoRestService,
        networkController: NetworkController
    ) = DeleteUserRepositoryImpl(goRestService, networkController)

    @Provides
    @Singleton
    fun provideUserListMapper() = UserListMapper()

    @Provides
    @Singleton
    fun provideAddUserMapper() = AddUserMapper()

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userListRepositoryImpl: UserListRepositoryImpl) =
        GetUserListUseCase(userListRepositoryImpl)

    @Provides
    @Singleton
    fun provideAddUserUseCase(addUserRepositoryImpl: AddUserRepositoryImpl) =
        AddUserUseCase(addUserRepositoryImpl)

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(deleteUserRepositoryImpl: DeleteUserRepositoryImpl) =
        DeleteUserUseCase(deleteUserRepositoryImpl)
}