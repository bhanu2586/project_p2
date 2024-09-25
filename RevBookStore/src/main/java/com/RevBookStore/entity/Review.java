
package com.RevBookStore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

	/* @ManyToOne */
	/*
	 * @JoinColumn(name = "product_id", nullable = false) private Products product;
	 */

    private int rating;
    private String reviewText;
    private long userId;

    // Getters and Setters

   

	/*
	 * public Products getProduct() { return product; }
	 * 
	 * public void setProduct(Products product) { this.product = product; }
	 */

    public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
