package com.konaire.teamwork.interactors.projects

import com.konaire.teamwork.models.ProjectsResponse
import com.konaire.teamwork.network.Api

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
interface ProjectsListInteractor {
    fun getProjects(
        uiScheduler: Scheduler = AndroidSchedulers.mainThread()
    ): Single<ProjectsResponse>
}

class ProjectsListInteractorImpl @Inject constructor(
    private val api: Api
): ProjectsListInteractor {
    override fun getProjects(uiScheduler: Scheduler): Single<ProjectsResponse> = api.getProjects().observeOn(uiScheduler)
}