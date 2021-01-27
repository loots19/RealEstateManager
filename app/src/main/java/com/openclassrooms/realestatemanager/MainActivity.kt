package com.openclassrooms.realestatemanager


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.openclassrooms.realestatemanager.auth.LoginViewModel
import com.openclassrooms.realestatemanager.auth.SplashActivity
import com.openclassrooms.realestatemanager.databinding.ActivityMainBinding
import com.openclassrooms.realestatemanager.fragment.ListFragment
import com.openclassrooms.realestatemanager.fragment.MapsFragment
import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mLoginViewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        UtilsKotlin.checkPermissionForImage(this)

        configureToolbar()
        configureDrawer()
        configureBottomNavigation()


        if (savedInstanceState == null) {
            val fragment = ListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                    .commit()
        }

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
            }
            R.id.price_conversion -> {
                startActivity(Intent(this@MainActivity, ConversionActivity::class.java))
            }
            R.id.simulator -> {
                startActivity(Intent(this@MainActivity, SimulatorActivity::class.java))
            }
            R.id.logOut -> {
                alertLogOut()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //click event on item in toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                startActivity(Intent(this@MainActivity, FilterActivity::class.java))

            }

        }
        return super.onOptionsItemSelected(item)
    }

    // initialize toolBar
    private fun configureToolbar() {
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDisplayShowTitleEnabled(false)
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

    // ---------------------------------------------------------
    // ----- Alert Dialog when workmates want disconnected -----
    // ---------------------------------------------------------
    private fun alertLogOut() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.titlle_alert)
        builder.setPositiveButton(R.string.yes) { _, _ ->
            logout()

        }
        builder.setNegativeButton(R.string.no) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    // ---------------------------------
    // ----- Configuring Observers -----
    // ---------------------------------
    private fun logout() {
        mLoginViewModel.logout()
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()

    }


}
