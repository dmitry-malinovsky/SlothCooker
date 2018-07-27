package com.example.dmalinovschi.activities

import android.content.Intent
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.dmalinovschi.adapters.TextInputLayoutAdapter
import com.example.dmalinovschi.services.IngredientsModelService
import com.example.dmalinovschi.utils.InputValidator
import com.example.dmalinovschi.utils.ValidatingTextWatcher
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import kotlinx.android.synthetic.main.create_ingrediant_activity.*

open class IngredientMainActivity : MainActivity() {
    internal lateinit var inputAdapter: TextInputLayoutAdapter
    internal lateinit var inputValidator: InputValidator

    internal var inputValidationResult: Boolean = false
    internal lateinit var ingredientsModelService: IngredientsModelService

    companion object {
        val NEW_INGREDIENT_KEY = "NEW INGREDIENT"
    }

    internal fun getInputDetails(): IngredientsListRowModel {
        return IngredientsListRowModel(
                0,
                inputAdapter.getText(ingredient_title_edit_layout),
                if (inputAdapter.getText(ingredient_protein_edit_layout).isEmpty()) 0 else inputAdapter.getText(ingredient_protein_edit_layout).toInt(),
                if (inputAdapter.getText(ingredient_carbs_edit_layout).isEmpty()) 0 else inputAdapter.getText(ingredient_carbs_edit_layout).toInt(),
                if (inputAdapter.getText(ingredient_fats_edit_layout).isEmpty()) 0 else inputAdapter.getText(ingredient_fats_edit_layout).toInt(),
                if (inputAdapter.getText(ingredient_ccal_edit_layout).isEmpty()) 0 else inputAdapter.getText(ingredient_ccal_edit_layout).toInt()
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

    internal fun setViewListeners() {
        save_ingredient_fab.setOnClickListener { confirmInput() }

        ingredient_title_edit_text.addTextChangedListener(ValidatingTextWatcher(inputValidator::validateTitle, ingredient_title_edit_layout))
        ingredient_protein_edit_text.addTextChangedListener(ValidatingTextWatcher(inputValidator::validateMacronutrients, ingredient_protein_edit_layout))
        ingredient_carbs_edit_text.addTextChangedListener(ValidatingTextWatcher(inputValidator::validateMacronutrients, ingredient_carbs_edit_layout))
        ingredient_fats_edit_text.addTextChangedListener(ValidatingTextWatcher(inputValidator::validateMacronutrients, ingredient_fats_edit_layout))
        ingredient_ccal_edit_text.addTextChangedListener(ValidatingTextWatcher(inputValidator::validateCcal, ingredient_ccal_edit_layout))
    }

    internal open fun confirmInput() {
        if (!validateInput()) {
            inputValidationResult = false
        } else {
            if (validateInput()) {
                inputValidationResult = true
                val inputIngredientDetails: IngredientsListRowModel = getInputDetails()
                ingredientsModelService.buildIngredientFromInput(inputIngredientDetails)

                val intent = Intent(this, IngredientsActivity::class.java)
                intent.putExtra(NEW_INGREDIENT_KEY, inputIngredientDetails)
                finish()
            }
        }
    }

    internal fun validateInput(): Boolean {
        return ((inputValidator.validateTitle(ingredient_title_edit_layout)) &&
                (inputValidator.validateMacronutrients(ingredient_protein_edit_layout)) &&
                (inputValidator.validateMacronutrients(ingredient_carbs_edit_layout)) &&
                (inputValidator.validateMacronutrients(ingredient_fats_edit_layout)) &&
                (inputValidator.validateCcal(ingredient_ccal_edit_layout)))
    }
}