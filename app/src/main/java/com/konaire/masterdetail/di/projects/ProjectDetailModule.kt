package com.konaire.masterdetail.di.projects

import com.konaire.masterdetail.interactors.projects.ProjectDetailInteractor
import com.konaire.masterdetail.interactors.projects.ProjectDetailInteractorImpl
import com.konaire.masterdetail.presenters.projects.ProjectDetailPresenter
import com.konaire.masterdetail.presenters.projects.ProjectDetailPresenterImpl
import com.konaire.masterdetail.ui.projects.ProjectDetailFragment
import com.konaire.masterdetail.ui.projects.ProjectDetailView

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