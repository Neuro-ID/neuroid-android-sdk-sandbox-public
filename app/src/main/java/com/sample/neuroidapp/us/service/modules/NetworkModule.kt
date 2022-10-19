package com.sample.neuroidapp.us.service.modules

import com.sample.neuroidapp.us.service.network.NIDNetworkHelper
import com.sample.neuroidapp.us.service.network.NIDServices
import com.sample.neuroidapp.us.service.network.NetworkInteractor
import com.sample.neuroidapp.us.service.network.NetworkInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesNIDServices(networkHelper: NIDNetworkHelper): NIDServices {
        return networkHelper.service
    }

    @Provides
    @Singleton
    fun provideNetworkInteractor(networkInteractorImpl: NetworkInteractorImpl): NetworkInteractor {
        return networkInteractorImpl
    }

}