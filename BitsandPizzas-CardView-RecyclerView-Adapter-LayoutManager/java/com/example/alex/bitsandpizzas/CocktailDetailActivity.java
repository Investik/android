package com.example.alex.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CocktailDetailActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_COCKTAIL = "cocktailNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);
        //Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Display details of the pizza
        int cocktailNo = (Integer) getIntent().getExtras().get(EXTRA_COCKTAIL);
        String cocktailName = Cocktail.cocktails[cocktailNo].getName();
        TextView textView = (TextView) findViewById(R.id.cocktail_text);
        textView.setText(cocktailName);
        int cocktailImage = Cocktail.cocktails[cocktailNo].getImageDescription();
        ImageView imageView = (ImageView)findViewById(R.id.cocktail_image);
        imageView.setImageResource(cocktailImage);
        imageView.setContentDescription(cocktailName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        TextView textView = (TextView)findViewById(R.id.cocktail_text);
        CharSequence cocktailName = textView.getText();
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, cocktailName);
        shareActionProvider.setShareIntent(intent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
