package com.sample.neuroidapp.us.service.modules

import com.sample.neuroidapp.us.service.domain.config.ConfigHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigureModule {

    @Provides
    @Singleton
    fun provideConfigHelper(): ConfigHelper {
        return ConfigHelper()
    }
}