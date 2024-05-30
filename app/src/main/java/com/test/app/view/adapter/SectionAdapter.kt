package com.test.app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.test.app.R
import com.test.app.model.SectionWithAlerts

class SectionAdapter (
    private val dataList: List<SectionWithAlerts>,
) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.section_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.icon.setImageResource(item.section.resId)
        holder.title.text = item.section.title
        holder.description.text = item.section.description
        val count = item.alerts.size
        holder.errorLeft.isVisible = false
        holder.errorCount.isVisible = false
        if (count > 0) {
            holder.errorLeft.isVisible = true
            holder.errorCount.isVisible = true
            holder.errorCount.text = count.toString()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon_img)
        val title: TextView = itemView.findViewById(R.id.title_item)
        val description: TextView = itemView.findViewById(R.id.decription_item)
        val errorCount: TextView = itemView.findViewById(R.id.error_count)
        val errorLeft: View = itemView.findViewById(R.id.error_left)
    }
}