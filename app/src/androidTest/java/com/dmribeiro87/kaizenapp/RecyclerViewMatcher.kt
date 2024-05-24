package com.dmribeiro87.kaizenapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position in RecyclerView with id $recyclerViewId")
            }

            public override fun matchesSafely(view: View): Boolean {
                val recyclerView = view.rootView.findViewById(recyclerViewId) as RecyclerView?
                val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position)
                    ?: return false
                return if (targetViewId == -1) {
                    view === viewHolder.itemView
                } else {
                    val targetView = viewHolder.itemView.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }
}