package com.example.alex.bitsandpizzas;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView topRecycler = (RecyclerView) layout.findViewById(R.id.Top_recycler);
        String[] names = {Pizza.pizzas[1].getName(), Cocktail.cocktails[1].getName()};
        int[] images = {Pizza.pizzas[1].getImageResourceId(), Cocktail.cocktails[1].getImageDescription()};
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        topRecycler.setLayoutManager(layoutManager);
        CaptionedImageAdapter adapter = new CaptionedImageAdapter(names, images);
        topRecycler.setAdapter(adapter);
        adapter.setListener(new CaptionedImageAdapter.Listener() {
            public void onClick(int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                    intent.putExtra(PizzaDetailActivity.EXTRA_PIZZANO, 1);
                    getActivity().startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(getActivity(), CocktailDetailActivity.class);
                    intent.putExtra(CocktailDetailActivity.EXTRA_COCKTAIL, 1);
                    getActivity().startActivity(intent);
                }
            }
        });

        return layout;

    }

}
