package com.konaire.masterdetail.network

import com.konaire.masterdetail.models.Project
import com.konaire.masterdetail.models.ProjectResponse
import com.konaire.masterdetail.models.ProjectsResponse

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Evgeny Eliseyev on 23/04/2018.
 */
interface Api {
    @GET("projects.json")
    fun getProjects(@Query("status") status: String = Project.Status.ALL): Single<ProjectsResponse>

    @GET("projects/{id}.json")
    fun getProject(
        @Path("id") id: Long
    ): Single<ProjectResponse>
}