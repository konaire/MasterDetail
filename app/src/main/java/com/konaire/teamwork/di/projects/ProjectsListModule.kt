package com.konaire.teamwork.di.projects

import com.konaire.teamwork.interactors.projects.ProjectsListInteractor
import com.konaire.teamwork.interactors.projects.ProjectsListInteractorImpl
import com.konaire.teamwork.presenters.projects.ProjectsListPresenter
import com.konaire.teamwork.presenters.projects.ProjectsListPresenterImpl
import com.konaire.teamwork.ui.projects.ProjectsListFragment
import com.konaire.teamwork.ui.projects.ProjectsListView

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