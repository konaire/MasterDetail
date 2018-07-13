package com.konaire.teamwork.di.projects

import com.konaire.teamwork.di.scopes.ActivityScope
import com.konaire.teamwork.util.Navigation

import dagger.Module
import dagger.Provides

/**
 * Created by Evgeny Eliseyev on 23/04/2018.
 */
@Module
class ProjectActivityModule {
    @ActivityScope
    @Provides
    fun provideNavigation(): Navigation = Navigation()
}