package com.example.pruebatecnicafrogtek.di

import com.example.pruebatecnicafrogtek.domain.repositories.ApiRepository
import com.example.pruebatecnicafrogtek.domain.repositories.ApiTask
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiRepositoryModule {
    @Binds
    abstract fun bindGetDataRepositoryTask(
        apiRepository: ApiRepository
    ): ApiTask
}