package com.konaire.masterdetail.interactors.projects

import com.konaire.masterdetail.models.ProjectResponse
import com.konaire.masterdetail.network.Api

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
interface ProjectDetailInteractor {
    fun getProject(
        id: Long, uiScheduler: Scheduler = AndroidSchedulers.mainThread()
    ): Single<ProjectResponse>
}

class ProjectDetailInteractorImpl @Inject constructor(
    private val api: Api
): ProjectDetailInteractor {
    override fun getProject(id: Long, uiScheduler: Scheduler): Single<ProjectResponse> = api.getProject(id).observeOn(uiScheduler)
}