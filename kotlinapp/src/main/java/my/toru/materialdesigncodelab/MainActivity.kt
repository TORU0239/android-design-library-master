package my.toru.materialdesigncodelab

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding Toolbar to Main Screen
        setSupportActionBar(toolbar)
        // Setting ViewPager for each Tabs
        setupViewPager(viewpager)
        // Setting Tabs inside toolbar
        tabs.setupWithViewPager(viewpager)
        // Create Navigation Drawer and inflate layout
        supportActionBar?.let{
            val indicator = VectorDrawableCompat.create(resources, R.drawable.ic_menu, theme)
            indicator?.setTint(ResourcesCompat.getColor(resources, R.color.white, theme))
            it.setHomeAsUpIndicator(indicator)
            it.setDisplayHomeAsUpEnabled(true)
        }

        nav_view.setNavigationItemSelectedListener {
            item: MenuItem ->
                item.isChecked = true
                drawer.closeDrawers()
                true
        }

        fab.setOnClickListener {
            Snackbar.make(it, "Hello Snackbar!", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setupViewPager(viewPager:ViewPager){
        val adapter = Adapter(supportFragmentManager)

        adapter.addFragment(ListContentFragment(), "List");
        adapter.addFragment(TileContentFragment(), "Tile");
        adapter.addFragment(CardContentFragment(), "Card");

        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == R.id.action_settings){
            return true
        }
        else if(id == android.R.id.home){
            drawer.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}

class Adapter(manager:FragmentManager): FragmentPagerAdapter(manager) {

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    internal fun addFragment(fragment:Fragment, title:String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence = fragmentTitleList[position]
}