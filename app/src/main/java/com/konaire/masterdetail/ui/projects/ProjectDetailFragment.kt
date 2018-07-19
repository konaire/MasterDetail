package com.konaire.masterdetail.ui.projects

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.konaire.masterdetail.R
import com.konaire.masterdetail.models.Project
import com.konaire.masterdetail.presenters.projects.ProjectDetailPresenter
import com.konaire.masterdetail.ui.BaseFragment
import com.konaire.masterdetail.ui.BaseLoadingView

import dagger.android.support.AndroidSupportInjection

import kotlinx.android.synthetic.main.fragment_project_detail.*

import javax.inject.Inject

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
interface ProjectDetailView: BaseLoadingView {
    fun updateProject(project: Project)
}

class ProjectDetailFragment: BaseFragment(), ProjectDetailView {
    @Inject lateinit var presenter: ProjectDetailPresenter

    companion object {
        private const val TAG = "PROJECT_DETAIL"
        private const val KEY_PROJECT = "KEY_PROJECT"

        fun create(project: Project): ProjectDetailFragment {
            val fragment = ProjectDetailFragment()
            val args = Bundle()

            args.putParcelable(KEY_PROJECT, project)
            fragment.arguments = args
            return fragment
        }
    }

    private fun getProject(): Project = arguments?.getParcelable(KEY_PROJECT) ?: throw IllegalArgumentException("Project can't be null. Please check that it has been already set.")

    private fun setProject(project: Project) = arguments?.putParcelable(KEY_PROJECT, project)

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_project_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val project = getProject()

        updateUI(project)
        presenter.getProject(project.id)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopSubscriptions()
    }

    override fun getTitle(): String = getProject().name

    override fun getFragmentTag(): String = TAG

    override fun updateProject(project: Project) {
        setProject(project)
        updateUI(project)
    }

    override fun showProgress() {
        progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = View.GONE
    }

    private fun updateUI(project: Project) {
        Glide.with(this).load(project.logo).apply(RequestOptions().placeholder(android.R.color.darker_gray)).into(image)

        name.text = project.name
        company.text = project.company.name
        circle.setImageResource(if (project.status == Project.Status.ACTIVE.toLowerCase()) R.drawable.ic_circle_green else R.drawable.ic_circle_red)
        star.setImageResource(if (project.starred) R.drawable.ic_star_filled else R.drawable.ic_star_border)

        createdDate.text = project.formattedCreatedDate()
        dateRange.text = getString(R.string.project_date_range, project.formattedStartDate(), project.formattedEndDate())
        tags.text = project.formattedTags()
        description.text = project.description

        if (tags.text.isNotEmpty()) {
            tagsTitle.visibility = View.VISIBLE
            tags.visibility = View.VISIBLE
        } else {
            tagsTitle.visibility = View.GONE
            tags.visibility = View.GONE
        }

        if (description.text.isNotEmpty()) {
            descriptionTitle.visibility = View.VISIBLE
            description.visibility = View.VISIBLE
        } else {
            descriptionTitle.visibility = View.GONE
            description.visibility = View.GONE
        }
    }
}