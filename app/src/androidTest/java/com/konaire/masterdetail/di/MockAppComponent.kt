package com.konaire.masterdetail.di

import com.konaire.masterdetail.MockApp
import com.konaire.masterdetail.ui.projects.ProjectActivityTest

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

/**
 * Created by Evgeny Eliseyev on 27/04/2018.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, MockAppModule::class, ActivityInjector::class])
interface MockAppComponent: AndroidInjector<MockApp> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MockApp>() {
        abstract override fun build(): MockAppComponent
    }

    fun inject(test: ProjectActivityTest)
}