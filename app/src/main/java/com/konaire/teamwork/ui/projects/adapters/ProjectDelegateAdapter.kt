package com.konaire.teamwork.ui.projects.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.konaire.teamwork.R
import com.konaire.teamwork.models.Project
import com.konaire.teamwork.ui.list.DelegateAdapter
import com.konaire.teamwork.ui.list.OnItemClickedListener
import com.konaire.teamwork.ui.list.ViewType

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
class ProjectDelegateAdapter(
    private val listener: OnItemClickedListener<Project>
): DelegateAdapter<ViewType> {
    class ProjectViewHolder(
        rootView: View,
        private val listener: OnItemClickedListener<Project>
    ): RecyclerView.ViewHolder(rootView) {
        fun bind(project: Project)  = with(itemView) {
            val image = findViewById<ImageView>(R.id.image)
            val circle = findViewById<ImageView>(R.id.circle)
            val name = findViewById<TextView>(R.id.name)
            val description = findViewById<TextView>(R.id.description)

            Glide.with(this).load(project.logo).apply(RequestOptions().placeholder(android.R.color.darker_gray)).into(image)
            circle.setImageResource(if (project.status == Project.Status.ACTIVE.toLowerCase()) R.drawable.ic_circle_green else R.drawable.ic_circle_red)

            name.text = project.name
            description.text = project.description

            setOnClickListener { listener.onItemClicked(project, this) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        ProjectViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_project, parent, false), listener)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: ViewType) =
        (holder as ProjectViewHolder).bind(item as Project)
}