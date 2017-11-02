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

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var mDrawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Adding Toolbar to Main screen
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        // Setting ViewPager for each Tabs
        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        setupViewPager(viewPager)
        // Set Tabs inside Toolbar
        val tabs = findViewById<View>(R.id.tabs) as TabLayout
        tabs.setupWithViewPager(viewPager)
        // Create Navigation drawer and inlfate layout
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        mDrawerLayout = findViewById<View>(R.id.drawer) as DrawerLayout
        // Adding menu icon to Toolbar
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            val indicator = VectorDrawableCompat.create(resources, R.drawable.ic_menu, theme)
            indicator!!.setTint(ResourcesCompat.getColor(resources, R.color.white, theme))
            supportActionBar.setHomeAsUpIndicator(indicator)
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // This method will trigger on item Click of navigation menu
            // Set item in checked state
            menuItem.isChecked = true

            // TODO: handle navigation

            // Closing drawer on item click
            mDrawerLayout!!.closeDrawers()
            true
        }
        // Adding Floating Action Button to bottom right of main view
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { v ->
            Snackbar.make(v, "Hello Snackbar!",
                    Snackbar.LENGTH_LONG).show()
        }
    }

    // Add Fragments to Tabs
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(ListContentFragment(), "List")
        adapter.addFragment(TileContentFragment(), "Tile")
        adapter.addFragment(CardContentFragment(), "Card")
        viewPager.adapter = adapter
    }

    internal class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        } else if (id == android.R.id.home) {
            mDrawerLayout!!.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}

