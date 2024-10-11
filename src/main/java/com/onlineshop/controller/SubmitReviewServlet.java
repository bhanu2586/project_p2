package com.onlineshop.controller;






import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineshop.entity.review;
import com.onlineshop.service.CustomerServiceInterface;
import com.onlineshop.utility.ServiceFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/SubmitReviewServlet")
public class SubmitReviewServlet extends HttpServlet {
   
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");
        //String userid=request.getParameter("userid");
        String ratingParam = request.getParameter("rating");
        String reviewText = request.getParameter("reviewText");

        if (productIdParam != null && ratingParam != null ) {
            
                int productId = Integer.parseInt(productIdParam);
               // int userid = Integer.parseInt(userid);
                int rating = Integer.parseInt(ratingParam);
                review rev = new review();
                rev.setProductId(productId);
                rev.setUserId(1); // Assume a user ID for now
                rev.setRating(rating);
                rev.setReviewText(reviewText);
                CustomerServiceInterface cService=ServiceFactory.getObject("customer");

                int addreview=cService.addReview(rev);
                if(addreview>0) {
                response.sendRedirect("reviews.jsp");
                }
                else {
                	response.sendRedirect("my-orders.jsp");
                	
                }
            
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required parameters are missing.");
        }
    }
}
