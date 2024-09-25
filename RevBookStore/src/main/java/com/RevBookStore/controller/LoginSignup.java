package com.RevBookStore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.User;
import com.RevBookStore.service.CustomerServiceInterface;
import com.RevBookStore.service.LoginSignupServiceInterface;
import com.RevBookStore.service.SellerServiceInterface;

@Controller
public class LoginSignup {
    
	@Autowired
	private LoginSignupServiceInterface serviceInterface;
	
	@Autowired
	private CustomerServiceInterface cservice;
	
	@Autowired
	private SellerServiceInterface sellerServiceInterface;
	
	@PostMapping("signup")
	public ModelAndView signup(
			@RequestParam("name") String name,@RequestParam("email") String email,
			@RequestParam("password") String password,@RequestParam("phone_number") String phone_number,
			@RequestParam("address") String address,@RequestParam("pincode") String pincode,@RequestParam("userType") String userType,HttpSession session
			) {
		
		
		
		ModelAndView mv = new ModelAndView();
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone_number(phone_number);
		user.setAddress(address);
		user.setPincode(pincode);
		user.setUserType(userType);
		
		boolean userRegistred = serviceInterface.signup(user);
		if(userRegistred) {
			mv.addObject("user", userRegistred);
			mv.setViewName("login.jsp");
			return mv;
		}else {
			String err = "User not registred...Please try again";
			mv.addObject("error",err);
			mv.setViewName("signup.jsp");
			return mv;
		}
	}
	@RequestMapping("login")
	public ModelAndView login(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		User u= serviceInterface.login(user);
		if(u.getUserType().equals("buyer")) {
			
			session.setAttribute("userId", u.getUserId());
			session.setAttribute("user-role", u.getUserType());
			session.setAttribute("name", u.getName());
			List<Products> products = cservice.viewProducts();
			mv.addObject("product_list",products);
			mv.setViewName("products.jsp");
		}
		else if(u.getUserType().equals("seller")) {
			Long userId = u.getUserId();
			session.setAttribute("user", u.getUserId());
			session.setAttribute("user-role", u.getUserType());
			session.setAttribute("name", u.getName());
			List<Products> products = sellerServiceInterface.viewProducts(userId);
			mv.addObject("product_list",products);
			mv.setViewName("/seller-views/inventory.jsp");
		}else {
			session.setAttribute("user", u.getUserId());
			mv.addObject("admin", u);
			mv.setViewName("admin/user.jsp");
		}
		return mv;
	}
}
