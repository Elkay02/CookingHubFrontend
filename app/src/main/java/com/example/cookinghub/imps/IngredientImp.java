package com.example.cookinghub.imps;

import com.example.cookinghub.classes.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientImp {
    public IngredientImp() {}
    public void insert(Ingredient ingredient) {
        insert(ingredient.getName(), ingredient.getRecipeId());
    }

    public void insert(String ingredient, long recipeId) {

    }

    public List<Ingredient> selectAllByRecipeId(long recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();


        return ingredients;
    }

    public boolean deleteAllByRecipeId(long recipeId) {
        return true;
    }

}
