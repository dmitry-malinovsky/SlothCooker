package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.dmalinovschi.adapters.TextInputLayoutAdapter
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.IngredientsModelService
import com.example.dmalinovschi.utils.TextValidator
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel


open class CreateIngredientActivity : IngredientsActivity() {
    private lateinit var textInputTitle: TextInputLayout
    private lateinit var textInputProtein: TextInputLayout
    private lateinit var textInputCarbs: TextInputLayout
    private lateinit var textInputFats: TextInputLayout
    private lateinit var textInputCcal: TextInputLayout
    internal lateinit var floatingActionButton: FloatingActionButton
    private lateinit var inputAdapter: TextInputLayoutAdapter

    internal lateinit var inputTitle: TextInputEditText
    internal lateinit var inputProtein: TextInputEditText
    internal lateinit var inputCarbs: TextInputEditText
    internal lateinit var inputFats: TextInputEditText
    internal lateinit var inputCcal: TextInputEditText

    internal var inputValidationResult: Boolean = false
    internal lateinit var ingredientsModelService: IngredientsModelService

    companion object {
        val NEW_INGREDIENT_KEY = "NEW INGREDIENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_ingrediant_activity)
        setUpToolbar(findViewById(R.id.back_toolbar))
        setViewElements()
        inputAdapter = TextInputLayoutAdapter()
        ingredientsModelService = IngredientsModelService(appDatabase)
    }

    internal fun getInputDetails(): IngredientsListRowModel {
        return IngredientsListRowModel(
                0,
                inputAdapter.getText(textInputTitle),
                if (inputAdapter.getText(textInputProtein).isEmpty()) 0 else inputAdapter.getText(textInputProtein).toInt(),
                if (inputAdapter.getText(textInputCarbs).isEmpty()) 0 else inputAdapter.getText(textInputCarbs).toInt(),
                if (inputAdapter.getText(textInputFats).isEmpty()) 0 else inputAdapter.getText(textInputFats).toInt(),
                if (inputAdapter.getText(textInputCcal).isEmpty()) 0 else inputAdapter.getText(textInputCcal).toInt()
        )
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


    private fun setViewElements() {
        textInputTitle = findViewById(R.id.ingredient_title_edit_layout)
        textInputProtein = findViewById(R.id.ingredient_protein_edit_layout)
        textInputCarbs = findViewById(R.id.ingredient_carbs_edit_layout)
        textInputFats = findViewById(R.id.ingredient_fats_edit_layout)
        textInputCcal = findViewById(R.id.ingredient_ccal_edit_layout)
        floatingActionButton = findViewById(R.id.save_ingredient_fab)

        inputTitle = findViewById(R.id.ingredient_title_edit_text)
        inputProtein = findViewById(R.id.ingredient_protein_edit_text)
        inputCarbs = findViewById(R.id.ingredient_carbs_edit_text)
        inputFats = findViewById(R.id.ingredient_fats_edit_text)
        inputCcal = findViewById(R.id.ingredient_ccal_edit_text)

        floatingActionButton.setOnClickListener { confirmInput() }
        inputTitle.addTextChangedListener(TextValidator(::validateTitle))
        inputProtein.addTextChangedListener(TextValidator(::validateProtein))
        inputCarbs.addTextChangedListener(TextValidator(::validateCarbs))
        inputFats.addTextChangedListener(TextValidator(::validateFat))
        inputCcal.addTextChangedListener(TextValidator(::validateCcal))
    }

    internal open fun confirmInput() {
        if (!validateTitle() and !validateProtein() and !validateCarbs() and !validateCcal() and !validateFat()) {
            inputValidationResult = false
        } else {
            if (validateTitle() and validateProtein() and validateCarbs() and validateCcal() and validateFat()) {
                inputValidationResult = true
                var inputIngredientDetails: IngredientsListRowModel = getInputDetails()
                ingredientsModelService.buildIngredientFromInput(inputIngredientDetails)

                val intent = Intent(this, IngredientsActivity::class.java)
                intent.putExtra(NEW_INGREDIENT_KEY, inputIngredientDetails)
                finish()
            }
        }
    }

    internal fun validateTitle(): Boolean {
        val title = inputAdapter.getText(textInputTitle)
        if (title.isEmpty()) {
            textInputTitle.error = "Title can't be empty"
            return false
        } else if (title.length > 30) {
            textInputTitle.error = "Title can't be longer than 50"
            return false
        } else {
            textInputTitle.error = null
            return true
        }
    }

    internal fun validateProtein(): Boolean {
        var protein = inputAdapter.getText(textInputProtein)

        if (protein.isEmpty()) {
            textInputProtein.error = null
            return true
        } else {
            if (Integer.parseInt(protein) > 100) {
                textInputProtein.error = "It cant be that much"
                return false
            } else {
                textInputProtein.error = null
                return true
            }
        }
    }

    internal fun validateCarbs(): Boolean {
        val carbs = inputAdapter.getText(textInputCarbs)
        if (carbs.isEmpty()) {
            textInputCarbs.error = null
            return true
        } else {
            if (Integer.parseInt(carbs) > 100) {
                textInputCarbs.error = "It cant be that much"
                return false
            } else {
                textInputCarbs.error = null
                return true
            }
        }
    }

    internal fun validateFat(): Boolean {
        val fats = inputAdapter.getText(textInputFats)
        if (fats.isEmpty()) {
            textInputFats.error = null
            return true
        } else {
            if (Integer.parseInt(fats) > 100) {
                textInputFats.error = "It cant be that much"
                return false
            } else {
                textInputFats.error = null
                return true
            }
        }
    }

    internal fun validateCcal(): Boolean {
        val ccal = inputAdapter.getText(textInputCcal)
        if (ccal.isEmpty()) {
            textInputCcal.error = null
            return true
        } else {
            if (Integer.parseInt(ccal) > 500) {
                textInputCcal.error = "It cant be that much"
                return false
            } else {
                textInputCcal.error = null
                return true
            }
        }
    }
}