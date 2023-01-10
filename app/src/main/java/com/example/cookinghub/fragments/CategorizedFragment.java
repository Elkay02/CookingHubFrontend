package com.example.cookinghub.fragments;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cookinghub.MainActivity;
import com.example.cookinghub.R;
import com.example.cookinghub.adapters.DatabaseAdapter;
import com.example.cookinghub.adapters.RecipeAdapter;
import com.example.cookinghub.classes.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class CategorizedFragment extends Fragment {
    private CategorizedFragmentListener categorizedFragmentListener;
    protected RecyclerView recipeRecyclerView;
    private TextView emptyView;
    protected RecipeAdapter recipeAdapter;
    protected DatabaseAdapter databaseAdapter;
    protected String currentCategory;
    public List<Recipe> recipes;
    public List<Recipe> recList;

    public CategorizedFragment() {
        // Required empty public constructor
        recipes = new ArrayList<>();
        databaseAdapter = new DatabaseAdapter();
    }

    public static Fragment newInstance(String category) {
        Fragment fragment;
        switch (category) {
            case "Appetizers":
                fragment = new AppetizersFragment();
                break;
            case "Hot Plates":
                fragment = new HotPlatesFragment();
                break;
            case "Cold Plates":
                fragment = new ColdPlatesFragment();
                break;
            case "Desserts":
                fragment = new DessertsFragment();
                break;
            default:
                fragment = new AppetizersFragment();
                break;
        }
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(getFragmentLayout(), container, false);

        Bundle args = getArguments();
        currentCategory = args.getString("category");
        recipeRecyclerView = rootView.findViewById(R.id.recyclerView);
        emptyView = rootView.findViewById(R.id.empty_view);
        recipeRecyclerView.setHasFixedSize(true);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();

    }


    @Override
    public void onDetach() {
        super.onDetach();
        categorizedFragmentListener = null;
    }

    public void refresh()  {
        String myUrl = "https://cookinghub-backend.azurewebsites.net/api/v1/recipes/?category=" + currentCategory;
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray arr = new JSONArray(response);

                            for (int i = 0; i < arr.length(); i++) {

                                JSONObject row = arr.getJSONObject(i);

                                Recipe rec = new Recipe(row.getString("name"), row.getString("category"), row.getString("description"), row.getString("image"));
                                recipes.add(rec);
                                Log.i("helloooo", String.valueOf(recipes));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(myRequest);



        toggleEmptyView();
        recipeAdapter = new RecipeAdapter(getActivity(), recipes);
        recipeAdapter.setRecipeListener(new RecipeAdapter.RecipeListener() {
            @Override
            public void onShowRecipe(Recipe recipe, Pair<View, String>[] pairs) {
                categorizedFragmentListener.onShowRecipe(recipe, pairs);
            }

            @Override
            public void onEditRecipe(Recipe recipe) {
                categorizedFragmentListener.onEditRecipe(recipe);
            }

            @Override
            public void onDeleteRecipe(long recipeId) throws JSONException {
                categorizedFragmentListener.onDeleteRecipe(recipeId);
                refresh();
            }
        });
        recipeRecyclerView.setAdapter(recipeAdapter);
    }

    private void toggleEmptyView() {
        if (recipes.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            recipeRecyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recipeRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    protected abstract @LayoutRes
    int getFragmentLayout();

    public interface CategorizedFragmentListener {
        void onShowRecipe(Recipe recipe, Pair<View, String>[] pairs);

        void onEditRecipe(Recipe recipe);

        void onDeleteRecipe(long recipeId);
    }
}
