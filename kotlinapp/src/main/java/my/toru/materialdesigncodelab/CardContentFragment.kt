package my.toru.materialdesigncodelab


import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_card.view.*


/**
 * A simple [Fragment] subclass.
 */
class CardContentFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val recyclerView = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView

        recyclerView.apply {
            adapter = CardContentAdapter(context)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
        return recyclerView
    }
}

class CardContentAdapter(ctx:Context) : RecyclerView.Adapter<CardContentViewHolder>(){

    private var places : Array<String>
    private var placeDesc : Array<String>
    private var placePictures = ArrayList<Drawable>()

    init{
        val resources = ctx.resources
        places = resources.getStringArray(R.array.places)
        placeDesc = resources.getStringArray(R.array.place_desc)

        val ta = resources.obtainTypedArray(R.array.places_picture)

        for(i in 0 until ta.length()){
            placePictures.add(i, ta.getDrawable(i))
        }
        ta.recycle()
    }

    override fun getItemCount() = 18

    override fun onBindViewHolder(holder: CardContentViewHolder?, position: Int) {
        holder?.bindData(placePictures[position % placePictures.size],
                places[position % places.size],
                placeDesc[position % placeDesc.size])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardContentViewHolder {
        return CardContentViewHolder(LayoutInflater.from(parent?.context), parent)
    }
}

class CardContentViewHolder(layoutInflater: LayoutInflater, parent:ViewGroup?) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_card, parent, false)) {
    internal fun bindData(placeDrawable: Drawable, name: String, desc: String) {
        itemView.card_image.setImageDrawable(placeDrawable)
        itemView.card_title.text = name
        itemView.card_text.text = desc

        with(itemView) {
            setOnClickListener {
                Toast.makeText(it.context, "Under Construction", Toast.LENGTH_SHORT).show()
            }
            action_button.setOnClickListener {
                Snackbar.make(this, "Action is Pressed", Snackbar.LENGTH_LONG).show()
            }
            favorite_button.setOnClickListener {
                Snackbar.make(this, "Added to Favourite", Snackbar.LENGTH_LONG).show()
            }
            share_button.setOnClickListener {
                Snackbar.make(this, "Share article", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}