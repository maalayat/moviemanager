package ec.solmedia.themoviedb.view.activity

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.commons.extensions.consume
import ec.solmedia.themoviedb.commons.extensions.snack
import ec.solmedia.themoviedb.view.fragment.NowPlayingFragment
import ec.solmedia.themoviedb.view.fragment.UpcomingFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit private var drawerToggle: ActionBarDrawerToggle
    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarDetail)

        setupInjection()
        setupListeners()
        setupDrawerToggle()
        setupSharedPreferences()

        if (savedInstanceState == null) {
            selectItem(nav_view.menu.getItem(0))
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun selectItem(item: MenuItem) {
        title = item.title
        when (item.itemId) {
            R.id.nav_now_playing -> drawer_layout.consume { showFragment(NowPlayingFragment(), true) }
            R.id.nav_upcoming -> drawer_layout.consume { showFragment(UpcomingFragment(), true) }
            R.id.nav_logout -> drawer_layout.consume { } //TODO logout
            else -> drawer_layout.consume { showFragment(NowPlayingFragment(), true) }
        }
    }

    private fun setupInjection() {
        TheMovieDBApp.mainComponent.inject(this)
    }

    private fun setupListeners() {
        fab.setOnClickListener { view ->
            view.snack("Replace with your own action") {}
        }

        nav_view.setNavigationItemSelectedListener({
            selectItem(it)
            true
        })
    }

    private fun setupDrawerToggle() {
        drawerToggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbarDetail,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(drawerToggle)
    }

    private fun setupSharedPreferences() {
        if (!sharedPreferences.contains(TheMovieDBApp.LOCALE_KEY)) {
            sharedPreferences.edit().putString(TheMovieDBApp.LOCALE_KEY, Locale.getDefault().language).apply()
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }

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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    private fun showFragment(fragment: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out,
                R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.flContent, fragment)
        ft.addToBackStack(null)
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
