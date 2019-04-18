package com.example.mygardener.ui;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.example.mygardener.PlantWateringService;
import com.example.mygardener.R;
import com.example.mygardener.provider.PlantContract;

public class AddPlantActivity extends AppCompatActivity {
    private RecyclerView mTypesRecyclerView;
    private PlantTypesAdapter mTypesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        // Plant types are displayed as a recycler view using PlantTypesAdapter
        mTypesAdapter = new PlantTypesAdapter(this);
        mTypesRecyclerView = (RecyclerView) findViewById(R.id.plant_types_recycler_view);
        mTypesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        mTypesRecyclerView.setAdapter(mTypesAdapter);

    }

    /**
     * Event handler to handle clicking on a plant type
     *
     * @param view
     */
    public void onPlantTypeClick(View view) {
        // When the chosen plant type is clicked, create a new plant and set the creation time and
        // water time to now
        // Extract the plant type from the tag
        ImageView imgView = (ImageView) view.findViewById(R.id.plant_type_image);
        int plantType = (int) imgView.getTag();
        long timeNow = System.currentTimeMillis();
        // Insert the new plant into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlantContract.PlantEntry.COLUMN_PLANT_TYPE, plantType);
        contentValues.put(PlantContract.PlantEntry.COLUMN_CREATION_TIME, timeNow);
        contentValues.put(PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME, timeNow);
        getContentResolver().insert(PlantContract.PlantEntry.CONTENT_URI, contentValues);
        PlantWateringService.startActionUpdatePlantWidgets(this);
        // Close this activity
        finish();
    }

    public void onBackButtonClick(View view) {
        finish();
    }
}