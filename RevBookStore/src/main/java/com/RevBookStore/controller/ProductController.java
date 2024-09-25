package com.RevBookStore.controller;




import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.orders;
import com.RevBookStore.service.SellerServiceInterface;


@Controller

@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private SellerServiceInterface sellerServiceInterface;
	
	@PostMapping
	public ModelAndView addProduct(@RequestParam("name") String name,
			                       @RequestParam("description") String description,
			                       @RequestParam("category") String category,
			                       @RequestParam("price") String price,
			                       @RequestParam("discount_price") String discount_price,
			                       @RequestParam("quantity") String quantity,
			                       @RequestParam("imageUrl") String imageUrl,
			                       HttpSession session) {
		System.out.println(name);
		System.out.println(description);
		System.out.println(category);
		System.out.println(price);
		System.out.println(imageUrl);
		Long userId = (Long) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		//if user-id is null 
		if(userId == null) {
			mv.setViewName("login.jsp");
			return mv;
		}
		
		double parsedPrice;
		try {
			parsedPrice = Double.parseDouble(price);
		}catch(NumberFormatException e) {
			String err = "invalid price format";
			mv.addObject("error",err);
			mv.setViewName("add.jsp");
			return mv;
		}
		
		Products product = new Products();
		product.setName(name);
		product.setDescription(description);
		product.setCategory(category);
		product.setPrice(parsedPrice);
		
		if(discount_price!=null) {
			try {
				double discountPrice = Double.parseDouble(discount_price);
				product.setDiscount_price(discountPrice);
				}catch(NumberFormatException e) {
					String err = "invalid price format";
					mv.addObject("error",err);
					mv.setViewName("add.jsp");
					return mv;
				}
		}
		double parsedQuantity;
		try {
			parsedQuantity = Double.parseDouble(quantity);
			product.setQuantity(parsedQuantity);
			}catch(NumberFormatException e) {
				String err = "invalid quantity format";
				mv.addObject("error",err);
				mv.setViewName("add.jsp");
				return mv;
			}
		product.setImageUrl(imageUrl);
		
		boolean productAdded = sellerServiceInterface.addProduct(userId,product);
		if(productAdded) {
			List<Products> products = sellerServiceInterface.viewProducts(userId);
			mv.addObject("product_list",products);
			mv.setViewName("/seller-views/inventory.jsp");
			return mv;
			
		}else {
			String err = "Something went wrong";
			mv.addObject("error",err);
			mv.setViewName("add.jsp");
			return mv;
		}
		
}
	

	@RequestMapping("viewProducts")
	public ModelAndView viewProducts(HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		List<Products> products = sellerServiceInterface.viewProducts(userId);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("product_list",products);
		mv.setViewName("/seller-views/inventory.jsp");
		return mv;
	}
	
	@RequestMapping("/editProducts")
	public ModelAndView editProducts(
	        @RequestParam("id") Long p_id,
	        @RequestParam("name") String name,
	        @RequestParam("description") String description,
	        @RequestParam("category") String category,
	        @RequestParam("discount_price") double discount_price,
	        @RequestParam("quantity") double quantity,
	        @RequestParam("imageUrl") String imageUrl, HttpSession session) {
		
		Long sellerId = (Long) session.getAttribute("user");
		System.out.println(sellerId);
		//Long pId = (Long) session.getAttribute("p_id");
		ModelAndView mv = new ModelAndView();
		Products products = new Products();
		products.setName(name);
		System.out.println(name);
		products.setDescription(description);
		System.out.println(description);
		products.setCategory(category);
		products.setSellerId(sellerId);
		
		
		products.setDiscount_price(discount_price);
		products.setQuantity(quantity);
		products.setImageUrl(imageUrl);
		System.out.println(imageUrl);
		Long userId = (Long) session.getAttribute("userId");
		boolean productEdited = sellerServiceInterface.editProduct(products,p_id);
		if(productEdited) {
			List<Products> product = sellerServiceInterface.viewProducts(userId);

			mv.addObject("product_list",product);
			mv.setViewName("/seller-views/inventory.jsp");
			return mv;
			
		}else {
			String err = "Something went wrong";
			mv.addObject("error",err);
			mv.setViewName("add.jsp");
			return mv;
		}
		
	}
	
	@PostMapping("deleteProduct")
	public ModelAndView deleteProduct(@RequestParam("id") Long productId,HttpSession session) {
	    ModelAndView mv = new ModelAndView();
//	    , @RequestParam("sellerId") Long sellerId
	    Long sellerId = (Long) session.getAttribute("user");
	    
	    boolean productDeleted = sellerServiceInterface.deleteProductById(productId, sellerId);

	    if (productDeleted) {
	        List<Products> products = sellerServiceInterface.viewProducts(sellerId);
	        mv.addObject("product_list", products);
	        mv.setViewName("/seller-views/inventory.jsp");
	    } else {
	        String err = "Failed to delete the product or unauthorized action";
	        mv.addObject("error", err);
	        mv.setViewName("error.jsp");
	    }

	    return mv;
	}
	
	@GetMapping("getOrders")
	public ModelAndView getOrders() {
		List<orders> order = sellerServiceInterface.getOrders();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("order_list",order);
		mv.setViewName("/seller-views/orders.jsp");
		return mv;
	}
		
	@PostMapping("updateStatus")
	public ModelAndView updateStatus(@RequestParam("orderId") long orderId,@RequestParam("productId") long productId,@RequestParam("status") String status,HttpSession session) {
//		@RequestParam("sellerId") long sellerId,
		Long sellerId = (Long) session.getAttribute("userId");
		ModelAndView mv = new ModelAndView();
		orders order = new orders();
		order.setOrder_Id(orderId);
		order.setProduct_Id(productId);
		order.setStatus(status);
		
		boolean statusUpdated = sellerServiceInterface.updateStatus(sellerId,order);
		mv.addObject("updateStatus",statusUpdated);
		mv.setViewName("/seller-views/orders.jsp");
		return mv;
	}
	
		//Process as follows First fetch the SellerProducts-IDS Based Upon their SellerID
		//next then using the ProductIDs follow up to find the ProductId mapped Reviews
		@GetMapping("/ProductReviews")
		public ModelAndView viewProductReviews(/* @RequestParam("sellerId") Long sellerId */HttpSession session) {
		    ModelAndView mv = new ModelAndView();
		    Long sellerId = (Long) session.getAttribute("userId");
		    
		   
		    List<Products> products = sellerServiceInterface.getProductsBySellerId(sellerId);
		    System.out.println("retrievedProducts: " + products);

		    if (products.isEmpty()) {
		        mv.addObject("message", "No products available.");
		        mv.setViewName("/seller-views/productReviews.jsp");
		        return mv;
		    }

		   
		    List<Long> productIds = products.stream().map(Products::getId).collect(Collectors.toList());;
		    System.out.println("productIdsFound: " + productIds);

		   
		    List<Review> reviews = sellerServiceInterface.getReviewsByProductId(productIds);
		    System.out.println("reviewsFound: " + reviews);

		    
		    mv.addObject("products", products);
		    mv.addObject("reviews", reviews);
		    mv.setViewName("/seller-views/productReviews.jsp");

		    return mv;
		}
		
}
