package com.example.alex.bitsandpizzas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CocktailMaterialFragment extends Fragment {


    public CocktailMaterialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pastaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_cocktail_material, container, false);
        String[] cocktailsNames = new String[Cocktail.cocktails.length];
        for (int i = 0; i < cocktailsNames.length; i++) {
            cocktailsNames[i] = Cocktail.cocktails[i].getName();
        }
        int[] cocktailsImages = new int[Cocktail.cocktails.length];
        for (int i = 0; i < cocktailsImages.length; i++) {
            cocktailsImages[i] = Cocktail.cocktails[i].getImageResourceId();
        }
        CaptionedImageAdapter adapter = new CaptionedImageAdapter(cocktailsNames, cocktailsImages);
        pastaRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pastaRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImageAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), CocktailDetailActivity.class);
                intent.putExtra(CocktailDetailActivity.EXTRA_COCKTAIL, position);
                getActivity().startActivity(intent);
            }
        });
        return pastaRecycler;
    }

}
