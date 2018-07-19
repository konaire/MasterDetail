package com.konaire.masterdetail.presenters.projects

import com.konaire.masterdetail.R
import com.konaire.masterdetail.interactors.projects.ProjectDetailInteractor
import com.konaire.masterdetail.presenters.BasePresenter
import com.konaire.masterdetail.ui.projects.ProjectDetailView

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