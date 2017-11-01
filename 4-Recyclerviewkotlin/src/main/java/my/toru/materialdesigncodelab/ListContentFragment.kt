package my.toru.materialdesigncodelab

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by wonyoung on 2017. 11. 1..
 */
class ListContentFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val recyclerView = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView
        val adapter = ContentAdapter(recyclerView.context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // TODO: make this more kotlin-wise!!
        return recyclerView
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list, parent, false)) {
        var avator: ImageView
        var name: TextView
        var description: TextView

        init {
            avator = itemView.findViewById<View>(R.id.list_avatar) as ImageView
            name = itemView.findViewById<View>(R.id.list_title) as TextView
            description = itemView.findViewById<View>(R.id.list_desc) as TextView
        }

        // TODO: you don't need to do like it. can you explain why?
    }

    /**
     * Adapter to display recycler view.
     */
    class ContentAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
        private val mPlaces: Array<String>
        private val mPlaceDesc: Array<String>
        private val mPlaceAvators: ArrayList<Drawable>

        init {
            val resources = context.resources
            mPlaces = resources.getStringArray(R.array.places)
            mPlaceDesc = resources.getStringArray(R.array.place_desc)
            val ta = resources.obtainTypedArray(R.array.place_avator)
            mPlaceAvators = ArrayList(ta.length())

            for (i in 0 until ta.length()) {
                mPlaceAvators.add(i, ta.getDrawable(i))
            }

            Log.w("TORU", "lengtj:: ${mPlaceAvators.size}")
            ta.recycle()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.size])
            holder.name.text = mPlaces[position % mPlaces.size]
            holder.description.text = mPlaceDesc[position % mPlaceDesc.size]
        }

        override fun getItemCount(): Int = LENGTH

        companion object {
            // Set numbers of List in RecyclerView.
            private val LENGTH = 18
        }
    }
}