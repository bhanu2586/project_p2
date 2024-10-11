package com.onlineshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineshop.entity.complaint;
import com.onlineshop.entity.review;
import com.onlineshop.service.CustomerServiceInterface;
import com.onlineshop.utility.ServiceFactory;

/**
 * Servlet implementation class SubmitComplaintServlet
 */
public class SubmitComplaintServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// TODO Auto-generated method stub
		String userIdStr = request.getParameter("userId");
        String retailerIdStr = request.getParameter("retailerId");
        String complaintText= request.getParameter("complaintText");
       

        // Check for null or empty values and handle them appropriately
        if (userIdStr == null || userIdStr.trim().isEmpty() ||
            retailerIdStr == null || retailerIdStr.trim().isEmpty()||complaintText == null || complaintText.trim().isEmpty()){

            response.getWriter().println("Error: Please fill in all required fields.");
            return; // Stop further processing if any field is empty
        }

        // Parse the userId and retailerId safely after checking for null
        int userId = Integer.parseInt(userIdStr);
        int retailerId = Integer.parseInt(retailerIdStr);
        
        complaint com = new complaint();
       
        com.setUserId(userId); // Assume a user ID for now
        com.setRetailerId(retailerId);
        com.setComplaintText(complaintText);
        CustomerServiceInterface cService=ServiceFactory.getObject("customer");

        int submit=cService.subcomservice(com);
        if(submit>0) {
            response.sendRedirect("index.jsp");
            }
            else {
            	response.sendRedirect("my-orders.jsp");
            	
            }
		}catch (NumberFormatException e) {
            // Handle the case where userId or retailerId is not a valid integer
            response.getWriter().println("Error: Invalid number format for userId or retailerId.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
        

	}

}
