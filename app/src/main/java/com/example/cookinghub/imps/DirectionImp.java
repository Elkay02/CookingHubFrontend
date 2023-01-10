package com.example.cookinghub.imps;

import android.content.ContentValues;

import com.example.cookinghub.classes.Direction;

import java.util.ArrayList;
import java.util.List;

public class DirectionImp {
    public DirectionImp() {}
    public void insert(Direction direction) {
        insert(direction.getBody(), direction.getRecipeId());
    }

    public void insert(String direction, long recipeId) {
        //db.insert(Config.TABLE_NAME, null, values);
    }

    public List<Direction> selectAllByRecipeId(long recipeId) {
        List<Direction> directions = new ArrayList<>();
        // retrieve all directions from database
        return directions;
    }

    public boolean deleteAllByRecipeId(long recipeId) {
        return true;
    }
}
