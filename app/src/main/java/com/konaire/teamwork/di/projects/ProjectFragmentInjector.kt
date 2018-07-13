package com.konaire.teamwork.di.projects

import com.konaire.teamwork.ui.projects.ProjectsListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Evgeny Eliseyev on 25/04/2018.
 */
@Module
interface ProjectFragmentInjector {
    @ContributesAndroidInjector(modules = [ProjectsListModule::class])
    fun provideProjectsListFragment(): ProjectsListFragment
}