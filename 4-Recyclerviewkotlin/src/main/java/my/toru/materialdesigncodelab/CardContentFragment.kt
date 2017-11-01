package my.toru.materialdesigncodelab

import android.content.Context
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
 * Created by wonyoung on 2017. 11. 1..
 */
class CardContentFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val recyclerView = inflater?.inflate(R.layout.recycler_view, container, false) as RecyclerView
        val adapter = ContentAdapter(recyclerView.context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // TODO: make above initialization part more kotlin-wise!!
        return recyclerView
    }
}

class ViewHolder(inflater:LayoutInflater, parent:ViewGroup?): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_card, parent,false)){
    var picture:ImageView
    var name:TextView
    var description:TextView

    init {
        picture = itemView.findViewById(R.id.card_image)
        name = itemView.findViewById(R.id.card_title)
        description = itemView.findViewById(R.id.card_text)
    }

    // TODO: we make this useless. can you guess?
}

class ContentAdapter(context: Context): RecyclerView.Adapter<ViewHolder>(){
    val LENGTH = 18
    var mPlaces:Array<String>
    var mPlaceDesc:Array<String>
    lateinit var mPlacePictures:ArrayList<Drawable>

    init {
        mPlaces = context.resources.getStringArray(R.array.places)
        mPlaceDesc = context.resources.getStringArray(R.array.place_desc)
        val ta = context.resources.obtainTypedArray(R.array.places_picture)
        for(i in 0 until ta.length()){
            mPlacePictures.add(i, ta.getDrawable(i))
        }
        ta.recycle()
    }

    override fun getItemCount(): Int = LENGTH

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.picture?.setImageDrawable(mPlacePictures[position % mPlacePictures.size])
        holder?.name?.text = mPlaces[position % mPlaces.size]
        holder?.description?.text = mPlaceDesc[position % mPlaceDesc.size]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent?.context), parent!!)

}