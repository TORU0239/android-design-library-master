/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package my.toru.materialdesigncodelab

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Provides UI for the view with Cards.
 */
class CardContentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val recyclerView = inflater.inflate(
                R.layout.recycler_view, container, false) as RecyclerView
        val adapter = ContentAdapter(recyclerView.context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return recyclerView
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_card, parent, false)) {
        var picture: ImageView
        var name: TextView
        var description: TextView

        init {
            picture = itemView.findViewById<View>(R.id.card_image) as ImageView
            name = itemView.findViewById<View>(R.id.card_title) as TextView
            description = itemView.findViewById<View>(R.id.card_text) as TextView
        }
    }

    /**
     * Adapter to display recycler view.
     */
    class ContentAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
        private val mPlaces: Array<String>
        private val mPlaceDesc: Array<String>
        private val mPlacePictures: ArrayList<Drawable>

        init {
            val resources = context.resources
            mPlaces = resources.getStringArray(R.array.places)
            mPlaceDesc = resources.getStringArray(R.array.place_desc)
            val a = resources.obtainTypedArray(R.array.places_picture)
            mPlacePictures = ArrayList()
            for (i in 0 until a.length()) {
                mPlacePictures.add(i, a.getDrawable(i))
            }
            a.recycle()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.size])
            holder.name.text = mPlaces[position % mPlaces.size]
            holder.description.text = mPlaceDesc[position % mPlaceDesc.size]
        }

        override fun getItemCount(): Int {
            return LENGTH
        }

        companion object {
            // Set numbers of List in RecyclerView.
            private val LENGTH = 18
        }
    }
}

