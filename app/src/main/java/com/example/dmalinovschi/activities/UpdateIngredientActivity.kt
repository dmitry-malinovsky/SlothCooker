package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.dmalinovschi.adapters.TextInputLayoutAdapter
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.IngredientsModelService
import com.example.dmalinovschi.utils.InputValidator
import com.example.dmalinovschi.utils.ValidatingTextWatcher
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import kotlinx.android.synthetic.main.create_ingrediant_activity.*

open class UpdateIngredientActivity : MainActivity() {
    private lateinit var inputAdapter: TextInputLayoutAdapter
    private lateinit var inputValidator: InputValidator
    private lateinit var ingredient: Ingredients

    private var inputValidationResult: Boolean = false
    private lateinit var ingredientsModelService: IngredientsModelService

    companion object {
        val NEW_INGREDIENT_KEY = "NEW INGREDIENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_ingrediant_activity)
        save_ingredient_fab.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_edit_24px))
        inputAdapter = TextInputLayoutAdapter()
        inputValidator = InputValidator(inputAdapter)
        ingredientsModelService = IngredientsModelService(appDatabase)
        setViewListeners()

        val extras = intent.extras
        ingredient = try {
            extras.getSerializable("SELECTED INGREDIENT") as Ingredients
        } catch (e: NullPointerException) {
            Ingredients()
        }

        setUpToolbar(findViewById(R.id.back_toolbar))
        ingredient_title_edit_text.setText(ingredient.ingredientTitle)
        ingredient_protein_edit_text.setText(ingredient.protein.toString())
        ingredient_carbs_edit_text.setText(ingredient.carb.toString())
        ingredient_fats_edit_text.setText(ingredient.fat.toString())
        ingredient_ccal_edit_text.setText(ingredient.ccal.toString())
    }

    internal fun getInputDetails(): IngredientsListRowModel {
        return IngredientsListRowModel(
                if (ingredient.ingredientId == null) 0 else ingredient.ingredientId,
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
        if (ingredient.ingredientTitle == null) {
            supportActionBar!!.title = "New ingredient"
        } else supportActionBar!!.title = "Update ingredient"
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

    private fun setViewListeners() {
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
                if (ingredient.ingredientId == 0) {
                    ingredientsModelService.buildIngredientFromInput(inputIngredientDetails)
                    val intent = Intent(this, IngredientsActivity::class.java)
                    intent.putExtra(NEW_INGREDIENT_KEY, inputIngredientDetails)
                } else
                    ingredientsModelService.updateIngredientByIdFromInput(ingredient.ingredientId, inputIngredientDetails)
                finish()
            }
        }
    }

    private fun validateInput(): Boolean {
        return ((inputValidator.validateTitle(ingredient_title_edit_layout)) &&
                (inputValidator.validateMacronutrients(ingredient_protein_edit_layout)) &&
                (inputValidator.validateMacronutrients(ingredient_carbs_edit_layout)) &&
                (inputValidator.validateMacronutrients(ingredient_fats_edit_layout)) &&
                (inputValidator.validateCcal(ingredient_ccal_edit_layout)))
    }
}
