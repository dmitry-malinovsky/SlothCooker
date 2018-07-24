package com.example.dmalinovschi.activities

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.dmalinovschi.playground.R

class CreateRecipeActivity : RecipesActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe_activity)
        setDatabase(this)
        setUpToolbar(findViewById(R.id.back_toolbar))

    }

    internal fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}