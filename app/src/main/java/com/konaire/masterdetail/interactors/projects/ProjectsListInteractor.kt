package com.konaire.masterdetail.interactors.projects

import com.konaire.masterdetail.models.ProjectsResponse
import com.konaire.masterdetail.network.Api

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.Collections

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
    override fun getProjects(uiScheduler: Scheduler): Single<ProjectsResponse> = api.getProjects().map { response ->
        Collections.sort(response.projects, compareBy { it.status })
        response
    }.observeOn(uiScheduler)
}