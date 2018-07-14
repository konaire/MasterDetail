package com.konaire.teamwork.ui.projects

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.contrib.RecyclerViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView

import com.konaire.teamwork.MockApp
import com.konaire.teamwork.R
import com.konaire.teamwork.di.DaggerMockAppComponent
import com.konaire.teamwork.espresso.RecyclerViewItemCountAssertion
import com.konaire.teamwork.models.Project
import com.konaire.teamwork.models.ProjectResponse
import com.konaire.teamwork.models.ProjectsResponse
import com.konaire.teamwork.models.Tag
import com.konaire.teamwork.network.Api

import io.reactivex.Single

import org.hamcrest.Matchers.*

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mockito.*

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 26/04/2018.
 */
@RunWith(AndroidJUnit4::class)
class ProjectActivityTest {
    @Rule @JvmField val activityRule: ActivityTestRule<ProjectActivity> = ActivityTestRule(ProjectActivity::class.java, true, false)

    @Inject lateinit var api: Api

    @Before
    fun setUp() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as MockApp
        (app.component as DaggerMockAppComponent).inject(this)
    }

    @Test
    fun checkWhenEverythingIsFine() {
        val projects = ProjectsResponse(listOf(
            Project(), Project(), Project(), Project(), Project(), Project()
        ).toMutableList())
        mockProjects(projects)
        activityRule.launchActivity(Intent())
        onView(withId(R.id.emptyView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.list)).check(RecyclerViewItemCountAssertion.withItemCount(6))
    }

    @Test
    fun checkWhenNetworkCrashes() {
        `when`(api.getProjects()).thenReturn(Single.error(Exception()))

        activityRule.launchActivity(Intent())
        onView(withId(R.id.emptyView)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).check(RecyclerViewItemCountAssertion.withItemCount(0))
    }

    @Test
    fun checkThatDescriptionViewsAreHiddenForEmptyDescription() {
        val project = Project()
        mockProject(ProjectResponse(project))
        mockProjects(ProjectsResponse(listOf(project).toMutableList()))

        activityRule.launchActivity(Intent())
        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.description)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkThatTagViewsAreHiddenIfNoTags() {
        val project = Project()
        mockProject(ProjectResponse(project))
        mockProjects(ProjectsResponse(listOf(project).toMutableList()))

        activityRule.launchActivity(Intent())
        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tags)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkThatOneTagIsDisplayedCorrectly() {
        val project = Project(tags = listOf(
            Tag(0, "Aaa", "#000000")
        ).toMutableList())
        mockProject(ProjectResponse(project))
        mockProjects(ProjectsResponse(listOf(project).toMutableList()))

        activityRule.launchActivity(Intent())
        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tags)).check(matches(withText("Aaa")))
    }

    @Test
    fun checkThatMultipleTagsAreDisplayedCorrectly() {
        val project = Project(tags = listOf(
            Tag(0, "Aaa", "#000000"),
            Tag(0, "Bbb", "#000000"),
            Tag(0, "Ccc", "#000000")
        ).toMutableList())
        mockProject(ProjectResponse(project))
        mockProjects(ProjectsResponse(listOf(project).toMutableList()))

        activityRule.launchActivity(Intent())
        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tags)).check(matches(withText("Aaa Bbb Ccc")))
    }

    @Test
    fun checkThatBackstackIsEmptyAfterSomeMess() {
        val projects = ProjectsResponse(listOf(
            Project(), Project()
        ).toMutableList())
        mockProjects(projects)
        mockProject(ProjectResponse(Project()))
        val activity = activityRule.launchActivity(Intent())

        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.pressBack()

        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Espresso.pressBack()

        assertEquals(0, activity.supportFragmentManager.backStackEntryCount)
    }

    private fun mockProjects(response: ProjectsResponse) {
        `when`(api.getProjects()).thenReturn(Single.just(response))
    }

    private fun mockProject(response: ProjectResponse) {
        `when`(api.getProject(anyLong())).thenReturn(Single.just(response))
    }
}