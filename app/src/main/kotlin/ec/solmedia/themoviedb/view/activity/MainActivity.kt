package ec.solmedia.themoviedb.view.activity

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.commons.extensions.consume
import ec.solmedia.themoviedb.view.fragment.adapter.MediaPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit private var drawerToggle: ActionBarDrawerToggle
    @Inject lateinit var sharedPreferences: SharedPreferences

    private var menuItemId: Int = 0

    companion object {
        val KEY_MENU_ITEM = "MainActivity:menuItem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarDetail)

        setupInjection()
        setupListeners()
        setupDrawerToggle()
        setupSharedPreferences()

        if (savedInstanceState == null) {
            selectItem(nav_view.menu.findItem(R.id.nav_tvshows))
        } else {
            menuItemId = savedInstanceState.getInt(KEY_MENU_ITEM)
            selectItem(nav_view.menu.findItem(menuItemId))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_MENU_ITEM, menuItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        menuItemId = savedInstanceState.getInt(KEY_MENU_ITEM)
    }

    private fun setupViewPagerTvShows() {
        val categories = listOf(
                getString(R.string.tv_category_popular),
                getString(R.string.tv_category_top_rated),
                getString(R.string.tv_category_on_the_air),
                getString(R.string.tv_category_airing_today)
        )
        val titles = listOf(
                getString(R.string.nav_item_tv_popular),
                getString(R.string.nav_item_tv_top_rated),
                getString(R.string.nav_item_tv_ontv),
                getString(R.string.nav_item_tv_airing_today)
        )

        viewPager.adapter = MediaPagerAdapter(supportFragmentManager, "tv", categories, titles)
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupViewPagerMovies() {
        val categories = listOf(
                getString(R.string.movie_category_now_playing),
                getString(R.string.movie_category_upcoming),
                getString(R.string.movie_category_popular),
                getString(R.string.movie_category_top_rated)
        )
        val titles = listOf(
                getString(R.string.nav_item_now_playing),
                getString(R.string.nav_item_upcoming),
                getString(R.string.nav_item_popular),
                getString(R.string.nav_item_top_rated)
        )

        viewPager.adapter = MediaPagerAdapter(supportFragmentManager, "movie", categories, titles)
        tabs.setupWithViewPager(viewPager)
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
        menuItemId = item.itemId
        when (item.itemId) {
            R.id.nav_tvshows -> drawer_layout.consume {
                setupViewPagerTvShows()
            }
            R.id.nav_movies -> drawer_layout.consume {
                setupViewPagerMovies()
            }
            R.id.nav_logout -> drawer_layout.consume { } //TODO logout
            else -> drawer_layout.consume {
                setupViewPagerTvShows()
            }
        }
    }

    private fun setupInjection() {
        TheMovieDBApp.mainComponent.inject(this)
    }

    private fun setupListeners() {
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
}
