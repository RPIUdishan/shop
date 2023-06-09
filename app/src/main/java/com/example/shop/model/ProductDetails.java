package com.example.shop.model;

public class ProductDetails {
    private String title;
    private String images;
    private String price;
    private String rating;
    private String description;

    public ProductDetails(String title, String images, String price, String rating, String description) {
        this.title = title;
        this.images = images;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public ProductDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
