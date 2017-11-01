package my.toru.materialdesigncodelab

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by wonyoung on 2017. 11. 1..
 */
class TileContentFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val recyclerView = inflater.inflate(
                R.layout.recycler_view, container, false) as RecyclerView
        val adapter = ContentAdapter(recyclerView.context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        // Set padding for Tiles
        val tilePadding = resources.getDimensionPixelSize(R.dimen.tile_padding)
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        return recyclerView
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_tile, parent, false)) {
        var picture: ImageView
        var name: TextView

        init {
            picture = itemView.findViewById<View>(R.id.tile_picture) as ImageView
            name = itemView.findViewById<View>(R.id.tile_title) as TextView
        }
    }

    /**
     * Adapter to display recycler view.
     */
    class ContentAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
        private val mPlaces: Array<String>
        private val mPlacePictures: ArrayList<Drawable>

        init {
            val resources = context.resources
            mPlaces = resources.getStringArray(R.array.places)
            val a = resources.obtainTypedArray(R.array.places_picture)
            mPlacePictures = ArrayList(a.length())
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