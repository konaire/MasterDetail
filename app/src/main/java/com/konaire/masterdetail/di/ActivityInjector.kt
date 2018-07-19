package com.konaire.masterdetail.di

import com.konaire.masterdetail.di.projects.ProjectActivityModule
import com.konaire.masterdetail.di.projects.ProjectFragmentInjector
import com.konaire.masterdetail.di.scopes.ActivityScope
import com.konaire.masterdetail.ui.projects.ProjectActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Evgeny Eliseyev on 25/04/2018.
 */
@Module
interface ActivityInjector {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ProjectActivityModule::class, ProjectFragmentInjector::class])
    fun provideProjectActivity(): ProjectActivity
}