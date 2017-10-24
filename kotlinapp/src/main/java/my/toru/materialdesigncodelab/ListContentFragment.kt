package my.toru.materialdesigncodelab

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by toruchoi on 24/10/2017.
 */
class ListContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val my_recycler_view = inflater?.inflate(R.layout.recycler_view, container, false) as RecyclerView
        val adapter = ContentAdapter(container?.context)

        my_recycler_view.let {
            it.adapter = adapter
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(activity)
        }
        return my_recycler_view
    }
}

class ContentAdapter: RecyclerView.Adapter<ViewHolder>{

    private var places : Array<String>
    private var placeDesc : Array<String>
    private var placeAvators : ArrayList<Drawable>

    constructor(ctx: Context?){
        val resources : Resources  = ctx!!.resources
        places = resources.getStringArray(R.array.places)
        placeDesc = resources.getStringArray(R.array.place_desc)

        val ta = resources.obtainTypedArray(R.array.place_avator)
        placeAvators = ArrayList(ta!!.length())

        for( i in 0 until ta.length()){
            placeAvators.add(i, ta.getDrawable(i))
        }

        ta.recycle()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(places[position % places.size], placeDesc[position % placeDesc.size],
                placeAvators[position % placeAvators.size])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context), parent)
    }

    override fun getItemCount(): Int = 18

}

class ViewHolder(inflater: LayoutInflater, parent:ViewGroup?): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list, parent, false)) {

    fun bindData(place:String, placeDesc:String, placeAvator:Drawable) {

        itemView.apply {
            list_title.text = place
            list_desc.text = placeDesc
            list_avatar.setImageDrawable(placeAvator)
        }
    }
}