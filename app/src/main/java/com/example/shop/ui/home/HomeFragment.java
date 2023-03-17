package com.example.shop.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.example.shop.Adapters.CategoryAdapter;
import com.example.shop.Adapters.ProductAdapter;
import com.example.shop.R;
import com.example.shop.databinding.FragmentHomeBinding;
import com.example.shop.model.Product;
import com.example.shop.utilis.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    // Array list for recycler view data source
    ArrayList<String> source;


    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LinearLayoutManager HorizontalLayout;


    private RequestQueue requestQueue;
    private List<Product> productList;


    RecyclerView recyclerViewProduct;

    String vrImageUrl = "https://i.im.ge/2023/03/18/D6kPI6.Pico-G2-4k-hero2-234-removebg-preview.png";

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        final ImageButton searchImageView = binding.searchButton;
        final ImageButton menuButton = binding.menuButton;
        final ImageButton searchButton = binding.searchButton;
        final ImageButton bagButton = binding.bagButton;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        final RecyclerView categoryRecyclerView  = binding.categoryRecyclerView;
        final RecyclerView recyclerViewProduct = binding.productRecyclerView;
        final ImageView vrImage = binding.vrImage;
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Glide.with(this.requireContext()).load(vrImageUrl).into(vrImage);

        recyclerView = view.findViewById(R.id.categoryRecyclerView);

        List<String> list =  new ArrayList<>();
        list.add("All Products");
        list.add("Popular");
        list.add("Recent");
        list.add("Expensive");

//        recyclerView = (RecyclerView)findViewById( R.id.recyclerview);


        RecyclerViewLayoutManager
                = new LinearLayoutManager(getContext());

        // Set LayoutManager on Recycler View
        categoryRecyclerView.setLayoutManager(
                RecyclerViewLayoutManager);

        // Adding items to RecyclerView.
//        AddItemsToRecyclerViewArrayList();

        // calling constructor of adapter
        // with source list as a parameter
//        adapter = new Adapter(source);

        // Set Horizontal Layout Manager
        // for Recycler view
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(
                this.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        categoryRecyclerView.setLayoutManager(HorizontalLayout);

        // Set adapter on recycler view
//        recyclerView.setAdapter(adapter);

        CategoryAdapter categoryAdapter = new CategoryAdapter(list);
        categoryRecyclerView.setAdapter(categoryAdapter);
//        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());



        LinearLayoutManager HorizontalLayout1 = new LinearLayoutManager(
                this.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
//        recyclerViewProduct = view.findViewById(R.id.productRecyclerView);
        recyclerViewProduct.setHasFixedSize(true);
        recyclerViewProduct.setLayoutManager(HorizontalLayout1);

        requestQueue = VolleySingleton.getmInstance(this.requireContext()).getRequestQueue();

        productList = new ArrayList<>();


//        ProductAdapter productAdapter = new ProductAdapter(requireContext(),productList);
//        recyclerViewProduct.setAdapter(productAdapter);

//        return root;
//    }
//
//    private List<Product> fetchMovies() {

        String url = "https://59b8726e92ccc3eb44b0c193eeef96f6.m.pipedream.net/products";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0 ; i < response.length() ; i++){

                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String title = jsonObject.getString("title");
//                                String overview = jsonObject.getString("overview");
                                String image = jsonObject.getString("images");
//                                Double rating = jsonObject.getDouble("rating");

                                Product product = new Product(title,image);
                                Log.d(">>>", String.valueOf(jsonObject));
                                productList.add(product);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(i==response.length()-1){
                                ProductAdapter productAdapter = new ProductAdapter(requireContext(),productList);
                                recyclerViewProduct.setAdapter(productAdapter);
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "onErrorResponse: ");
            }
        });

        requestQueue.add(jsonArrayRequest);

//        return productList;
        return root;
//    }


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        String[] list = {"All Products", "Featured", "Popular", "Recent", "Expensive"};
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
    }
}