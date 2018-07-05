package com.example.dmalinovschi.playground

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem


open class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    fun setToolbar() {
        mDrawerLayout = findViewById(R.id.drawer_layout)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            onOptionsItemSelected(menuItem)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        mDrawerLayout.closeDrawers()
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            R.id.nav_ingredients -> {
                if (this is IngredientsActivity) {
                    mDrawerLayout.closeDrawers()
                } else
                    startActivity(Intent(this, IngredientsActivity::class.java))
                true
            }
            R.id.nav_labels -> {
                print("Open labels list")
                true
            }
            R.id.nav_recipes -> {
                if (this is RecipesActivity) {
                    mDrawerLayout.closeDrawers()
                } else
                    startActivity(Intent(this, RecipesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

