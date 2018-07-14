package com.konaire.teamwork.di.projects

import com.konaire.teamwork.interactors.projects.ProjectDetailInteractor
import com.konaire.teamwork.interactors.projects.ProjectDetailInteractorImpl
import com.konaire.teamwork.presenters.projects.ProjectDetailPresenter
import com.konaire.teamwork.presenters.projects.ProjectDetailPresenterImpl
import com.konaire.teamwork.ui.projects.ProjectDetailFragment
import com.konaire.teamwork.ui.projects.ProjectDetailView

import dagger.Binds
import dagger.Module

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
@Module
interface ProjectDetailModule {
    @Binds fun provideProjectDetailInteractor(interactor: ProjectDetailInteractorImpl): ProjectDetailInteractor

    @Binds fun provideProjectDetailPresenter(presenter: ProjectDetailPresenterImpl): ProjectDetailPresenter

    @Binds fun provideProjectDetailView(fragment: ProjectDetailFragment): ProjectDetailView
}