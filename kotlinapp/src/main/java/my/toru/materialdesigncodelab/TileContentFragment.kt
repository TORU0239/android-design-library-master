package my.toru.materialdesigncodelab


import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_tile.view.*


/**
 * A simple [Fragment] subclass.
 */
class TileContentFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val my_recycler_view = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView
        val titleContentAdapter = TitleContentAdapter(my_recycler_view.context)

        my_recycler_view.apply {
            val tilePadding = resources.getDimensionPixelSize(R.dimen.tile_padding)
            adapter = titleContentAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            setPadding(tilePadding,tilePadding,tilePadding,tilePadding)
        }
        return my_recycler_view
    }
}

class TitleContentAdapter(ctx:Context?): RecyclerView.Adapter<TitleViewHolder>(){
    private var places : Array<String>
    private var placePictures : ArrayList<Drawable>

    init {
        val resources : Resources?  = ctx?.resources
        places = resources!!.getStringArray(R.array.places)

        val ta = resources.obtainTypedArray(R.array.places_picture)
        placePictures = ArrayList(ta!!.length())

        for( i in 0 until ta.length()){
            placePictures.add(i, ta.getDrawable(i))
        }

        ta.recycle()
    }

    override fun onBindViewHolder(holder: TitleViewHolder?, position: Int) {
        Log.w("Toru" , "" + places.size  + ", " + placePictures.size)
        holder?.bindData(places[position % places.size], placePictures[position % placePictures.size])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TitleViewHolder {
        return TitleViewHolder(LayoutInflater.from(parent?.context), parent)
    }

    override fun getItemCount(): Int = 18

}

class TitleViewHolder(inflater: LayoutInflater, parent:ViewGroup?): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_tile, parent, false)) {

    fun bindData(place:String, placePicture: Drawable) {

        itemView.apply {
            tile_picture.setImageDrawable(placePicture)
            tile_title.text = place
        }
    }
}
