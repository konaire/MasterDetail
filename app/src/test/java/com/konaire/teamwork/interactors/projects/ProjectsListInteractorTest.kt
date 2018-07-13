package com.konaire.teamwork.interactors.projects

import com.konaire.teamwork.models.ProjectResponse
import com.konaire.teamwork.network.Api

import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber

import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

import java.util.concurrent.TimeUnit

/**
 * Created by Evgeny Eliseyev on 26/04/2018.
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class ProjectsListInteractorTest {
    @Mock private lateinit var api: Api

    @InjectMocks private lateinit var scheduler: TestScheduler
    @InjectMocks private lateinit var interactor: ProjectsListInteractorImpl

    @Test
    fun isRequestShowsEmptyList() {
        mockNetwork(ProjectResponse("", ArrayList()))

        val subscriber = TestSubscriber.create<ProjectResponse>()
        interactor.getProjects(scheduler).toFlowable().subscribe(subscriber)
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS)
        scheduler.triggerActions()

        subscriber.assertValue { response -> response.projects.isEmpty() }
    }

    private fun mockNetwork(response: ProjectResponse) {
        `when`(api.getProjects()).thenReturn(Single.just(response))
    }
}