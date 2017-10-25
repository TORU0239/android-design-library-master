package my.toru.materialdesigncodelab

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by toruchoi on 25/10/2017.
 */
class DetailActivity:AppCompatActivity(){

    companion object {
        val EXTRA_POSITION =  "position"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // CollapsingToolbar layout
        val position = intent.getIntExtra(EXTRA_POSITION, 0)
        val places = resources.getStringArray(R.array.places)
        collapsing_toolbar.title = places[position % places.size]

        val placeDetails = resources.getStringArray(R.array.place_details)
        place_detail.text = placeDetails[position % placeDetails.size]

        val placePictures = resources.obtainTypedArray(R.array.places_picture)
        image.setImageDrawable(placePictures.getDrawable(position % placePictures.length()))

        placePictures.recycle()
    }
}