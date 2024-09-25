package com.RevBookStore.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.RevBookStore.entity.Favorite;
import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.ShoppingCart;
import com.RevBookStore.entity.User;
import com.RevBookStore.entity.orders;
import com.RevBookStore.service.CustomerServiceInterface;

@Controller
@RequestMapping("/buyer")
public class CustomerController {
	
	@Autowired
	private CustomerServiceInterface cservice;
	
	@RequestMapping("cart")
	public ModelAndView viewCartProducts(HttpSession session) {

		ModelAndView mv = new ModelAndView();

		Long customerId = (Long) session.getAttribute("userId");

		List<ShoppingCart> cartItems = cservice.viewCartProducts(customerId);
		mv.addObject("products", cartItems);
		mv.setViewName("/cart.jsp");

		// List<ShoppingCart> cart = cservice.viewCartProducts();

		return mv;
	}
	@RequestMapping("addProductsToCarts")
	public ModelAndView addProductsToCarts(@RequestParam("productId") Long productId, HttpSession session)
	{
		Long customerId = (Long) session.getAttribute("userId");
		
		boolean isAdded = cservice.addProductsToCarts(productId,customerId);	
		
		ModelAndView mv = new ModelAndView();
		if(isAdded)
		{
			List<ShoppingCart> cartItems = cservice.viewCartProducts(customerId);
			mv.addObject("products", cartItems);
			mv.setViewName("/cart.jsp");
		}
		else 
		{
			mv.addObject("message", "Failed to add product to cart");
			mv.setViewName("index.jsp");
		}
		return mv;
		
	}
	
	@RequestMapping("removeProductFromCart")
	public ModelAndView removeProductFromCart(@RequestParam("productId") Long productId,
			HttpSession session) {
		
		Long customerId = (Long) session.getAttribute("userId");

		ModelAndView mv = new ModelAndView();
		if ("delete".equals("delete")) {

			String ss = "Product List is ";

			ShoppingCart cart = cservice.findCartByCustomerId(customerId);
			if (cart == null) {
				// Cart doesn't exist for this customer
				ss = "Cart is empty";
				mv.addObject("message", ss);
				mv.setViewName("/cart.jsp");
			} else {
				boolean isRemoved = cservice.removeProductFromCart(customerId, productId);

				if (isRemoved) {
					List<ShoppingCart> cartItems = cservice.viewCartProducts(customerId);
					mv.addObject("products", cartItems);
					mv.setViewName("/cart.jsp");// Redirect to cart view after successful removal

				} else {
					mv.addObject("error", "Product not found in cart.");
					mv.setViewName("cart.jsp");

				}
			}

		}
		return mv;

	}
	@RequestMapping("viewProducts")
	public ModelAndView viewProducts() {
		List<Products> products = cservice.viewProducts();
		ModelAndView mv = new ModelAndView();
		mv.addObject("product_list",products);
		mv.setViewName("/products.jsp");
		return mv;
	}
	
	@GetMapping("viewProductDetails")
	public ModelAndView viewProductDetails(@RequestParam("id") Long id,HttpSession session) {
		
		/* Long id = (Long) session.getAttribute("id"); */
		ModelAndView mv = new ModelAndView();
		
		Products product = cservice.viewProductDeatils(id);
		mv.addObject("product", product);
		mv.setViewName("/productinfo.jsp");
		return mv;
	}
	
	@RequestMapping("updateQuantity")
	public ModelAndView updateQuantity(@RequestParam("productId") Long productId,@RequestParam("quantity") String quantity, HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Long customerId = (Long) session.getAttribute("userId");
		 
		int count = cservice.updateQuantity(productId,customerId,quantity);
		List<ShoppingCart> cartItems = cservice.viewCartProducts(customerId);
		mv.addObject("products", cartItems);
		mv.setViewName("/cart.jsp");
		/*
		 * mv.addObject("quantity",count);
		 * 
		 * mv.setViewName("/cart.jsp");
		 */
		return mv;
	}
	
	
	@RequestMapping("addProductToFavorite")
	public ModelAndView addProductToFavorite(@RequestParam("productId") Long productId, HttpSession session) {
		Long customerId = (Long) session.getAttribute("userId");
//		Long productId = (long) 11;
//		Long customerId = (long) 101;

		boolean isAdded = cservice.addProductToFavorite(productId, customerId);

		ModelAndView mv = new ModelAndView();
		if (isAdded) {
			List<Products> products = cservice.viewProducts();
			mv.addObject("product_list",products);
			mv.setViewName("/products.jsp");
			/*
			 * mv.addObject("message", "product added successfully");
			 * mv.setViewName("/products.jsp");
			 */
		} else {
			mv.addObject("message", "Failed to add product as favorite");
			mv.setViewName("/products.jsp");
		}
		return mv;

	}
	
	@GetMapping("wishlist")
	public ModelAndView viewFavorite(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Long customerId = (Long) session.getAttribute("userId");
		
		List<Favorite> fav = cservice.viewFavorite(customerId);
		
		mv.addObject("fav_list",fav);
		mv.setViewName("/favorites.jsp");

		return mv;
	}
	
	@RequestMapping("removeFromFavorite")
	public ModelAndView removeFromFavorite(HttpSession session,@RequestParam("productId") Long productId)
	{
		ModelAndView mv = new ModelAndView();
		Long customerId = (Long) session.getAttribute("userId");
		
		boolean isRemoved = cservice.removeFromFavorite(customerId,productId) ;

			if (isRemoved) {
				List<Favorite> fav = cservice.viewFavorite(customerId);
				
				mv.addObject("fav_list",fav);
				mv.setViewName("/favorites.jsp"); 

			}else { 
				mv.addObject("error", "Failed.");
				mv.setViewName("/favorites.jsp");

			}
		
		return mv;
		
	}
	@RequestMapping("submitreview")
	public ModelAndView submitreview(@RequestParam("productId") String productId,@RequestParam("rating") String rate,@RequestParam("reviewText") String reviewtext,HttpSession session) {
		 // int producId = Integer.parseInt(productId);
	      // int userid = Integer.parseInt(userid);
	       int rating = Integer.parseInt(rate);
	       Long customerId = (Long) session.getAttribute("userId");
	       long productID = Long.parseLong(productId);
	       int cid=customerId.intValue();
		 Review rev = new Review();
	     rev.setProductId(productID);
	     rev.setUserId(customerId); // Assume a user ID for now
	     rev.setRating(rating);
	     rev.setReviewText(reviewtext);
			
			int i=cservice.submitreview(rev);
			ModelAndView mv=new ModelAndView();
			Products product = cservice.viewProductDeatils(productID);
			mv.addObject("product", product);
			mv.setViewName("/productinfo.jsp");
			return mv;
		

	}
	
	@RequestMapping("checkout")
	public ModelAndView checkout(HttpSession session,@RequestParam("address") String address,@RequestParam("pincode") String pincode,@RequestParam("phoneNumber") String pno,@RequestParam("paymentMethod") String pm) {
		int i=0;
		Long customerId = (Long) session.getAttribute("userId");
		int pcode=Integer.parseInt(pincode);
		
		ModelAndView mv = new ModelAndView();
		
		User user= cservice.checkout(customerId);
		List<ShoppingCart> ll=cservice.getcartdetails(customerId);
		for(ShoppingCart shc:ll) {
			orders order = new orders();
			order.setOrder_date(new Timestamp(System.currentTimeMillis()));
			order.setPayment_mode(pm);
			order.setPhone_Number(pno);
			order.setProduct_Id(shc.getProductId());
			order.setTotal_price(shc.getPrice());
			order.setShippingAddress(address);
			order.setPincode(pcode);
			order.setUser_Id(customerId);
			order.setStatus("Pending");
		
			 i = cservice.saveorder(order);
			
			
			
			
		}
		mv.addObject("product", i);
		mv.setViewName("/hello.jsp");
		return mv;
	}
	
}
