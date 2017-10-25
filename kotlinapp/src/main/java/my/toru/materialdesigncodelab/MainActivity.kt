package my.toru.materialdesigncodelab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding Toolbar to Main Screen
        setSupportActionBar(toolbar)

        // Setting ViewPager for each Tabs
        setupViewPager(viewpager)
    }

    private fun setupViewPager(viewPager:ViewPager){
        val adapter = Adapter(supportFragmentManager)

        adapter.addFragment(ListContentFragment(), "List");
        adapter.addFragment(TileContentFragment(), "Tile");
        adapter.addFragment(CardContentFragment(), "Card");

        viewPager.adapter = adapter
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