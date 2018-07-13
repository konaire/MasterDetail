package com.konaire.teamwork.network

import com.konaire.teamwork.models.ProjectResponse

import io.reactivex.Single

import retrofit2.http.GET

/**
 * Created by Evgeny Eliseyev on 23/04/2018.
 */
interface Api {
    @GET("projects.json")
    fun getProjects(): Single<ProjectResponse>
}