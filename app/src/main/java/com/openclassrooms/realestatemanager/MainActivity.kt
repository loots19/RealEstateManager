package com.openclassrooms.realestatemanager


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.openclassrooms.realestatemanager.detailActivity.DetailActivity
import com.openclassrooms.realestatemanager.fragment.ListFragment
import com.openclassrooms.realestatemanager.fragment.MapsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()
        configureDrawer()
        configureBottomNavigation()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    // click event on bottomNavigation
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_list -> {
                val listFragment = ListFragment.newInstance()
                openFragment(listFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_map -> {
                val mapFragment = MapsFragment.newInstance()
                openFragment(mapFragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    // change fragment
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    //click event on drawer_layout
    @SuppressLint("ShowToast")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_property -> {
                startActivity(Intent(this@MainActivity, CreateProperty::class.java))
                Toast.makeText(this, "U have to create someThing", LENGTH_SHORT).show()
            }
            R.id.price_conversion -> {
                Toast.makeText(this, "U have to convert someThing", LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, ConversionActivity::class.java))
            }
            R.id.simulator -> {
                startActivity(Intent(this@MainActivity, SimulatorActivity::class.java))

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //click event on item in toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, "U have to search someThing", LENGTH_SHORT).show()
            }
            R.id.edit -> {
                // start detail activity for test
                startActivity(Intent(this@MainActivity, DetailActivity::class.java))

            }

        }
        return super.onOptionsItemSelected(item)
    }
    // initialize toolBar
    private fun configureToolbar() {
        setSupportActionBar(toolbar as Toolbar?)
    }

    // initialize drawer layout
    private fun configureDrawer() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_search_black_24dp)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    // initialize bottomNavigationView
    private fun configureBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        nav_view.setNavigationItemSelectedListener(this)
    }


}
