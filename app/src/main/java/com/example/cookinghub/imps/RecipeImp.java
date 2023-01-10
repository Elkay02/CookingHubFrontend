package com.example.cookinghub.imps;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cookinghub.CreateRecipeActivity;
import com.example.cookinghub.MainActivity;
import com.example.cookinghub.classes.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeImp {
    private IngredientImp ingredientImp;
    private DirectionImp directionImp;

    public RecipeImp() {

        ingredientImp = new IngredientImp();
        directionImp = new DirectionImp();
    }

    public long insert(Recipe recipe) {
        if (recipe.getIngredients() == null || recipe.getDirections() == null)
            throw new IllegalStateException("Cannot insert recipe: the recipe is incomplete.");

        long newRecipeId = insert(recipe.getName(), recipe.getCategory(), recipe.getDescription(), recipe.getImagePath());
//        recipe.getIngredients()
//                .forEach(ingredient -> {
//                    ingredient.setRecipeId(newRecipeId);
//                    ingredientImp.insert(ingredient);
//
//                });
//        recipe.getDirections()
//                .forEach(direction -> {
//                    direction.setRecipeId(newRecipeId);
//                    directionImp.insert(direction);
//
//                });

        return newRecipeId;
    }

    private long insert(String name, String category, String description, String imagePath) {
        return 0;
    }

    public List<Recipe> selectAllByCategory(String category) {
        List<Recipe> arr = new ArrayList<>();
        return arr;
    }


    public boolean deleteById(long id) {
        return true;
    }
    public interface IResult {
        public void notifySuccess(String response);

        public void notifyError(VolleyError error);
    }
}
