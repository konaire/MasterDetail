package com.konaire.masterdetail.di

import android.content.Context

import com.konaire.masterdetail.MockApp
import com.konaire.masterdetail.network.Api

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