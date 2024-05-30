package com.test.app.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomOffsetDecoration(private val bottomOffset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        // Apply the bottom offset only if the item is not the last one
        if (position != itemCount - 1) {
            outRect.bottom = bottomOffset
        }
    }
}