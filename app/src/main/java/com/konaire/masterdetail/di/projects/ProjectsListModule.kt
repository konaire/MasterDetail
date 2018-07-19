package com.konaire.masterdetail.di.projects

import com.konaire.masterdetail.interactors.projects.ProjectsListInteractor
import com.konaire.masterdetail.interactors.projects.ProjectsListInteractorImpl
import com.konaire.masterdetail.presenters.projects.ProjectsListPresenter
import com.konaire.masterdetail.presenters.projects.ProjectsListPresenterImpl
import com.konaire.masterdetail.ui.projects.ProjectsListFragment
import com.konaire.masterdetail.ui.projects.ProjectsListView

import dagger.Binds
import dagger.Module

/**
 * Created by Evgeny Eliseyev on 23/04/2018.
 */
@Module
interface ProjectsListModule {
    @Binds fun provideProjectsListInteractor(interactor: ProjectsListInteractorImpl): ProjectsListInteractor

    @Binds fun provideProjectsListPresenter(presenter: ProjectsListPresenterImpl): ProjectsListPresenter

    @Binds fun provideProjectsListView(fragment: ProjectsListFragment): ProjectsListView
}