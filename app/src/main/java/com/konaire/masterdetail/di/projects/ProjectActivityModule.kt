package com.konaire.masterdetail.di.projects

import com.konaire.masterdetail.di.scopes.ActivityScope
import com.konaire.masterdetail.util.Navigation

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