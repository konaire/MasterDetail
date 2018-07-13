package com.konaire.teamwork.di

import com.konaire.teamwork.di.projects.ProjectActivityModule
import com.konaire.teamwork.di.projects.ProjectFragmentInjector
import com.konaire.teamwork.di.scopes.ActivityScope
import com.konaire.teamwork.ui.projects.ProjectActivity

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