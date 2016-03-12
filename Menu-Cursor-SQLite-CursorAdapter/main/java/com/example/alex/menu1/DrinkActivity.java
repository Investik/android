package com.example.alex.menu1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
        try {
            SQLiteOpenHelper menuDatabaseHelper = new MenuDatabaseHelper(this);
            SQLiteDatabase db = menuDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFvorite = (cursor.getInt(3)==1);
                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFvorite);

                ImageView photo=(ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e){
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_LONG).show();
        }
    }

    public void onFavoriteClicked(View view){
        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", favorite.isChecked());


        SQLiteOpenHelper menuDatabaseHelper = new MenuDatabaseHelper(this);
        try {
            SQLiteDatabase db = menuDatabaseHelper.getWritableDatabase();
            db.update("DRINK", drinkValues, "_id = ?", new String[]{Integer.toString(drinkNo)});
            db.close();
        } catch (SQLiteException e){
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_LONG).show();
        }

    }
}
