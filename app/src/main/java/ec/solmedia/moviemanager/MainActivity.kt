package ec.solmedia.moviemanager

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ec.solmedia.moviemanager.commons.extensions.consume
import ec.solmedia.moviemanager.commons.extensions.snack
import ec.solmedia.moviemanager.view.fragment.NowPlayingFragment
import ec.solmedia.moviemanager.view.fragment.UpcomingFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarDetail)

        setupListeners()
        setupNavigationDrawer()
        showFragment(NowPlayingFragment(), true)
    }

    private fun setupListeners() {
        fab.setOnClickListener { view ->
            view.snack("Replace with your own action") {}
        }

        //val navigationView = findViewById(R.id.nav_view) as NavigationView
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun setupNavigationDrawer() {
        //val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbarDetail, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager;
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }

        //val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> consume { } //TODO settings
        else -> super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.nav_now_playing -> drawer_layout.consume { showFragment(NowPlayingFragment(), true) }
        R.id.nav_upcoming -> drawer_layout.consume { showFragment(UpcomingFragment(), true) }
        R.id.nav_logout -> drawer_layout.consume { } //TODO logout
        else -> super.onOptionsItemSelected(item)
    }

    private fun showFragment(fragment: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack();
        }
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out,
                R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.add(R.id.flContent, fragment)
        ft.addToBackStack("fragBack");
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val firts = manager.getBackStackEntryAt(0)
            manager.popBackStack(firts.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
