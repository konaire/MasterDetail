package com.konaire.teamwork.di

import android.content.Context

import com.konaire.teamwork.MockApp
import com.konaire.teamwork.network.Api

import dagger.Module
import dagger.Provides

import org.mockito.Mockito

import javax.inject.Singleton

/**
 * Created by Evgeny Eliseyev on 27/04/2018.
 */
@Module
class MockAppModule {
    @Singleton
    @Provides
    fun provideContext(app: MockApp): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideApi(): Api = Mockito.mock(Api::class.java)
}