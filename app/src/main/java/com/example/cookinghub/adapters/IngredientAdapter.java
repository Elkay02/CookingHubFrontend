package com.example.cookinghub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookinghub.R;
import com.example.cookinghub.classes.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;
    private Context mContext;
    private IngredientListener ingredientListener;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        mContext = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item_row,
                        parent, false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientText;
        ImageView wasteBin;

        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientText = itemView.findViewById(R.id.ingredientText);

        }

        public void bind(Ingredient ingredient) {
            ingredientText.setText(ingredient.getName());
        }
    }

    public void setIngredientListener(IngredientListener ingredientListener) {
        this.ingredientListener = ingredientListener;
    }

    public interface IngredientListener {
        void onDeleteIngredient(int position);
    }
}
