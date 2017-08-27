package com.star.sud.web.controller;
/*@Author Sudarshan*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.star.sud.GoogleMapLocation;

@Controller
public class StarMapContoller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "login/login";
	}

	@RequestMapping(value = "/google/loaction-track", method = RequestMethod.GET)
	public String getResultFromGoogleLoaction(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam String lattitude, @RequestParam String longitude, HttpSession session) {

		String location = GoogleMapLocation.getLocationAddress(lattitude, longitude);
		model.addAttribute("location", location);

		return "/welcome/welcome-page";
	}

}
