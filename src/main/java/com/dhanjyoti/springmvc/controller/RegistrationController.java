package com.dhanjyoti.springmvc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dhanjyoti.springmvc.model.KycDocument;
import com.dhanjyoti.springmvc.model.Users;
import com.dhanjyoti.springmvc.service.RegisterService;

@Controller
@RequestMapping("/")
@SessionAttributes
public class RegistrationController {
	
	@Autowired
	RegisterService registerService;

	@RequestMapping(value = { "/newCust" }, method = RequestMethod.GET)
	public ModelAndView newCustomer(@ModelAttribute("users") Users users) {
		ModelAndView mav = new ModelAndView("registration");	
		return mav;
	}	
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 * @throws IOException 
	 */
	@RequestMapping(value ="/newCust", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("users") @Valid Users user, BindingResult result,
			ModelMap model, @RequestParam("files") MultipartFile[] files) throws IOException {
		ModelAndView mav = new ModelAndView("registrationsuccess");
		
		
		if (result.hasErrors()) {
			System.out.println("registration  ERROR"+result.getFieldError().getField());
			return new ModelAndView("registration");
		} 
		
		System.out.println("files" +files.length);

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		/*if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/
		boolean status= getKycDocuments(files, user);
		
		if(!status) {
			model.addAttribute("error", true);
			return new ModelAndView("registration");
		}
		
		System.out.println("size::" +user.getKycDocList().size());
		
		registerService.saveUser(user);
		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		return mav;
	}
	
	@ModelAttribute
	private void addAttributes(ModelMap model) {
	    model.addAttribute("users", new Users());
	}
	
	@ModelAttribute("users")
	public Users getUsers(){
	    return  new Users();
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	
	private boolean getKycDocuments(MultipartFile[] files, Users user) throws IOException{
		
		for(MultipartFile file : files) {
			System.out.println("file >>>" +file.getOriginalFilename());
			if(file.getOriginalFilename() !=null && !file.getOriginalFilename().isEmpty()) {
				KycDocument doc = new KycDocument();
				doc.setKycType("D");
				doc.setUser(user);
				doc.setDocDesc(file.getOriginalFilename());
				
				byte [] byteArr=file.getBytes();
				InputStream inputStream = new ByteArrayInputStream(byteArr);
				doc.setDoc(byteArr);
				
				user.addKycDocument(doc);
			} else {
				return false;
			}
		
		}
		return true;
	}
	
}
