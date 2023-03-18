package com.example.shop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.shop.DetailsActivity;
import com.example.shop.R;
import com.example.shop.model.Product;
import com.example.shop.model.ProductDetails;
import com.example.shop.utilis.VolleySingleton;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductView> {
    private RequestQueue requestQueue;
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        productList = products;
        Log.d("Size", String.valueOf(products.size()));
    }

    @NonNull
    @Override
    public ProductAdapter.ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductView holder, int position) {

        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        Glide.with(context).load(product.getUrl()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("title", product.getTitle());
                Log.d("dfsdf", product.getTitle());

                requestQueue = VolleySingleton.getmInstance(context).getRequestQueue();
                String url = "https://59b8726e92ccc3eb44b0c193eeef96f6.m.pipedream.net/featured";
                Log.d("!!!xxx!>>>", v.getContext().toString());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String title = response.getString("title");
                                    String images = response.getString("images");
                                    String price = response.getString("price");
                                    String rating = response.getString("rating");
                                    String description = response.getString("description");


                                    Intent intent = new Intent(context, DetailsActivity.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", response.getString("title"));
                                    bundle.putString("images", response.getString("images"));
                                    bundle.putString("price", response.getString("price"));
                                    bundle.putString("rating", response.getString("rating"));
                                    bundle.putString("description", response.getString("description"));

                                    intent.putExtras(bundle);

                                    context.startActivity(intent);
//                                        Log.d("Res", date);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductView extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        ConstraintLayout constraintLayout;

        public ProductView(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.product_text_view);
            imageView = itemView.findViewById(R.id.product_image);

            constraintLayout = itemView.findViewById(R.id.product_layout);
        }
    }
}
