package com.example.dmalinovschi.playground

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import com.example.dmalinovschi.adapters.MainAdapter
import com.example.dmalinovschi.playground.persistance.AppDatabase
import com.example.dmalinovschi.playground.persistance.DatabaseInitialiser
import com.example.dmalinovschi.playground.persistance.models.Ingredients
import com.example.dmalinovschi.playground.persistance.models.Recipes
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeListModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeRowModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)

        appDatabase = AppDatabase.getInMemoryDatabase(this)
        populateDb(appDatabase)

        println("THIS IS MY STRING +++++++++++++++++++++++++++++++++++++++++++++++")

        var recipes: List<Recipes> = appDatabase.recipesModel().allRecipes
        var temp : MutableList<RecipeRowModel> = mutableListOf()

        for (recipe in recipes) {
            temp.add (RecipeRowModel(
                    recipe.totalCarbs,
                    recipe.totalProtein,
                    recipe.totalFat,
                    recipe.totalCcal,
                    recipe.title,
                    recipe.recipeId
            ))
        }

        var recipeListModel = RecipeListModel(temp)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        var button: Button = findViewById(R.id.add_recipe_button)

        if (recipes.isNotEmpty()) {
            button.visibility = View.GONE;
            recyclerView_main.adapter = MainAdapter(appDatabase, recipeListModel)
        } else {
            button.visibility = View.VISIBLE;
            recyclerView_main.setBackgroundColor(Color.GRAY)
        }

        mDrawerLayout.addDrawerListener(
                object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        // Respond when the drawer's position changes
                    }

                    override fun onDrawerOpened(drawerView: View) {
                        // Respond when the drawer is opened
                    }

                    override fun onDrawerClosed(drawerView: View) {
                        // Respond when the drawer is closed
                    }

                    override fun onDrawerStateChanged(newState: Int) {
                        // Respond when the drawer motion state changes
                    }
                })
    }


    private fun populateDb(appDatabase: AppDatabase) {
        DatabaseInitialiser.populateSync(appDatabase);
    }

    public fun fetchDb(): MutableList<Ingredients>? {
        return appDatabase.ingredientModel().allIngredients

    }


}
