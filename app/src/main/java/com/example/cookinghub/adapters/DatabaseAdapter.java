package com.example.cookinghub.adapters;

import android.content.Context;

import com.example.cookinghub.classes.Recipe;
import com.example.cookinghub.imps.RecipeImp;

import org.json.JSONException;

import java.util.List;

public class DatabaseAdapter {
    private Context mContext;
    public DatabaseAdapter() {
//        dbHelper = new SQLiteDatabaseHelper(context, DATABASE_NAME, DATABASE_VERSION);
    }
    private RecipeImp recipeImp;

    public long addNewRecipe(Recipe recipe) {
        return recipeImp.insert(recipe);
    }

    public void deleteRecipe(long recipeId) {
        recipeImp.deleteById(recipeId);
    }

    public List<Recipe> getAllRecipesByCategory(String category) {
        return recipeImp.selectAllByCategory(category);
    }
}
