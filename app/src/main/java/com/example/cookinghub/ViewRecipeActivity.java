package com.example.cookinghub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.cookinghub.adapters.DatabaseAdapter;
import com.example.cookinghub.adapters.ViewPagerAdapter;
import com.example.cookinghub.classes.Recipe;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class ViewRecipeActivity extends ToolbarActivity{
    private ViewPagerAdapter mAdapter;
    private Recipe currentRecipe;
    private DatabaseAdapter databaseAdapter;

    private ImageView mRecipeImage;
    private TextView mRecipeDescription;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        currentRecipe = getIntent().getParcelableExtra("recipe");
        databaseAdapter = new DatabaseAdapter();
        findViewsById();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(currentRecipe.getName());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecipeImage.setImageURI(Uri.fromFile(new File(currentRecipe.getImagePath())));
        mRecipeDescription.setText(currentRecipe.getDescription());

        mTabLayout.bringToFront();
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), currentRecipe);

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_recipe:
                Intent data = new Intent();
                data.putExtra("recipeId", currentRecipe.getId());
                setResult(Codes.RECIPE_TO_DELETE, data);
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViewsById() {
        mRecipeImage = findViewById(R.id.recipe_image);
        mRecipeDescription = findViewById(R.id.recipe_description);
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
    }
}
