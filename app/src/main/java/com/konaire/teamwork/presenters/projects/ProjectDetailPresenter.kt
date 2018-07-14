package com.konaire.teamwork.presenters.projects

import com.konaire.teamwork.R
import com.konaire.teamwork.interactors.projects.ProjectDetailInteractor
import com.konaire.teamwork.presenters.BasePresenter
import com.konaire.teamwork.ui.projects.ProjectDetailView

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
abstract class ProjectDetailPresenter: BasePresenter() {
    abstract fun getProject(id: Long)
}

class ProjectDetailPresenterImpl @Inject constructor(
    private val interactor: ProjectDetailInteractor,
    private val view: ProjectDetailView
): ProjectDetailPresenter() {
    override fun getProject(id: Long) {
        view.showProgress()
        disposables.add(interactor.getProject(id)
            .doFinally { view.hideProgress() }
            .subscribe(
                { response -> view.updateProject(response.project) },
                { view.showMessage(R.string.network_error) }
            )
        )
    }
}