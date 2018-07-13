package com.konaire.teamwork.presenters.projects

import com.konaire.teamwork.R
import com.konaire.teamwork.interactors.projects.ProjectsListInteractor
import com.konaire.teamwork.presenters.BasePresenter
import com.konaire.teamwork.ui.projects.ProjectsListView

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
abstract class ProjectsListPresenter: BasePresenter() {
    abstract fun getProjects()
}

class ProjectsListPresenterImpl @Inject constructor(
    private val interactor: ProjectsListInteractor,
    private val view: ProjectsListView
): ProjectsListPresenter() {
    override fun getProjects() {
        view.showProgress()
        disposables.add(interactor.getProjects()
            .doFinally { view.hideProgress() }
            .subscribe(
                { response -> view.showData(ArrayList(response.projects)) },
                { view.showMessage(R.string.network_error) }
            )
        )
    }
}