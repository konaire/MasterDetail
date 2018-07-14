package com.konaire.teamwork.interactors.projects

import com.konaire.teamwork.models.Project
import com.konaire.teamwork.models.ProjectsResponse
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
    fun isProjectsSortedCorrectlyAfterLoading() {
        val projects = listOf(
            Project(name = "A", status = Project.Status.ACTIVE),
            Project(name = "B", status = Project.Status.ARCHIVED),
            Project(name = "C", status = Project.Status.ARCHIVED),
            Project(name = "D", status = Project.Status.ACTIVE),
            Project(name = "E", status = Project.Status.ARCHIVED)
        ).toMutableList()

        mockNetwork(ProjectsResponse(projects))
        val subscriber = TestSubscriber.create<ProjectsResponse>()
        interactor.getProjects(scheduler).toFlowable().subscribe(subscriber)
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS)
        scheduler.triggerActions()

        subscriber.assertValue { response ->
            response.projects.size == 5 &&
            response.projects[0].name == "A" &&
            response.projects[1].name == "D" &&
            response.projects[2].name == "B" &&
            response.projects[3].name == "C" &&
            response.projects[4].name == "E"
        }
    }

    private fun mockNetwork(response: ProjectsResponse) {
        `when`(api.getProjects()).thenReturn(Single.just(response))
    }
}