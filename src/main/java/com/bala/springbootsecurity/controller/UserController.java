package com.bala.springbootsecurity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bala.springbootsecurity.dao.UserDao;
import com.bala.springbootsecurity.model.User;
import com.bala.springbootsecurity.service.SecurityService;
import com.bala.springbootsecurity.service.UserDetailsServiceImpl;
import com.bala.springbootsecurity.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author bala
 */
@SessionAttributes("email")
@Controller
@Slf4j
public class UserController
{
	@Autowired
	private UserDao userDao;

	private User user;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	static final String username = null;

	@RequestMapping(value = "error", method = RequestMethod.GET)
	public String handleError(Model model)
	{

		return "error";
	}

	@RequestMapping(value = "/handle-error", method = RequestMethod.GET)
	public String handleErrorWithMessage(Model model)
	{

		return "error/handleError";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model)
	{
		log.info("logout the user");
		model.addAttribute("user", new User());
		model.addAttribute("title", "Login");
		model.addAttribute("message", "logout successfully!!!");
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model)
	{
		model.addAttribute("title", "Register");
		model.addAttribute("user", new User());
		return "register";

	}

	@RequestMapping("/getById/{id}")
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable("id") String id)
	{
		User user = null;

		user = userDao.getById(id);
		log.info("usto be deleted the use.", user);
		System.out.println(user);

		if (user == null)
		{
			log.error("Unable to delete. User with id {} not found.", id);

		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") String id)
	{
		String mail = "";
		System.out.println(id);

		User user = userDao.getById(id);
		if (user == null)
		{
			log.error("Unable to delete. User with id {} not found.", id);
			/*
			 * return new ResponseEntity(new
			 * CustomErrorType("Unable to delete. User with id " + id +
			 * " not found."), HttpStatus.NOT_FOUND);
			 */
		}

		log.info("usto be deleted the use.", user);

		userDao.delete(user);

		log.info("usto be deleted the use.", user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@ResponseBody
	@RequestMapping(value = "/get-by-email/{email}")
	public User getByEmail(@PathVariable(value = "email") String email)
	{
		String userId;
		User user = null;
		log.info("email is {}", email);
		log.info("user s email ", email);
		try
		{
			user = userDao.getByEmail(email);
			if (user != null)
			{
				log.info("user is not null.", user);
			}
			else
			{
				log.info("user is  null.", user);
			}

		}
		catch (Exception ex)
		{
			log.error("user is  null", user);
		}
		return user;
	}

	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result)
	{

		if (result.hasErrors())
		{
			log.info("login the hasErrors.", "logout");

			return "login";

		}
		else
		{
			User currentUser = userDao.getById(user.getId());
			if (currentUser == null)
			{
				log.error("Unable to update. User with id {} not found.", user.getId());

			}
			userDao.save(user);

			return "dashboard";
		}

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String myProfile(Model model)
	{

		model.addAttribute("title", "Profile");
		return "profile";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String myHome(Model model)
	{

		model.addAttribute("title", "Profile");
		return "home";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model)
	{
		// userValidator.validate(userForm, bindingResult);
		log.info("UserController:registration:before binding the new user.{}", "User");
		if (bindingResult.hasErrors())
		{
			return "register";
		}
		User user1 = userDao.getByEmail(user.getEmail());

		if (user1 != null)
		{
			model.addAttribute("message", "user already exists!!!");
			return "register";
		}
		//	user.setLastUpdated(new Date());
		//user.setDateCreated(new Date());

		log.info("before saving the new user.", "User");
		userService.save(user);
		securityService.autologin(user.getEmail(), user.getPasswordConfirm());

		log.info("UserController:before redirect:/welcome**************.", "User");
		model.addAttribute("message", "You have been registered successfully.You can continue by log in..");

		log.info("UserController:registration:You have been registered successfully.*****.", "User");
		return "success";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout)
	{
		model.addAttribute("user", new User());
		model.addAttribute("title", "Login");

		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/", "/welcome"}, method = RequestMethod.GET)
	public ModelAndView welcome(Model model)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		model.addAttribute("message", "You have logged in successfully.");
		log.info("UserController:welcome. roleName {}", "roleName");
		return modelAndView;
	}

	public User getLoggedUser()
	{
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}