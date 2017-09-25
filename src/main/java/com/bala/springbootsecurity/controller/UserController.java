package com.bala.springbootsecurity.controller;

 
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.Valid;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bala.springbootsecurity.dao.UserDao;
import com.bala.springbootsecurity.model.User;
import com.bala.springbootsecurity.service.SecurityService;
import com.bala.springbootsecurity.service.UserService;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author bala
 */
@SessionAttributes("email")
@Controller
public class UserController {
	@Autowired
	private UserDao userDao;

	private User user;
	
 

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /create --> Create a new user and save it in the database.
	 * 
	 * @param email
	 *            User's email
	 * @param name
	 *            User's name
	 * @return A string describing if the user is succesfully created or not.
	 */

	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

	@InitBinder("expense")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "date",
                                    new CustomDateEditor(dateFormatter, true));
    }

	 
 
@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String handleError(Model model) {
		 
		return "error";
	}

	@RequestMapping(value = "/handle-error", method = RequestMethod.GET)
	public String handleErrorWithMessage(Model model) {
		 
		return "error/handleError";
	}


	 

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		logger.info("logou the use.", "logout");
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
         model.addAttribute("title", "Register" );
		model.addAttribute("user", new User());
		return "register";

	}

	@RequestMapping("/getById/{id}")
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		User user = null;

		user = userDao.getById(id);
		logger.info("usto be deleted the use.", user);
		System.out.println(user);

		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			/*return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);*/
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		String mail = "";
		System.out.println(id);

		User user = userDao.getById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			/*return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);*/
		}

		logger.info("usto be deleted the use.", user);

		userDao.delete(user);

		logger.info("usto be deleted the use.", user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * /get-by-email --> Return the id for the user having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
	@ResponseBody
	@RequestMapping(value = "/get-by-email/{email}")
	public User getByEmail(@PathVariable(value = "email") String email) {
		String userId;
		User user = null;
		System.out.println(email);
		logger.info("user s email ", email);
		try {
			user = userDao.getByEmail(email);
			System.out.println(user);
			if (user != null) {
				System.out.println(user);
				logger.info("user is not null.", user);
			} else {
				System.out.println(user);
				logger.info("user is  null.", user);
			}

		} catch (Exception ex) {
			logger.info("user is  null", user);
			System.out.println(ex);
		}
		return user;
	}




 
	@RequestMapping("/update")
	@ResponseBody
	   public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			logger.info("login the hasErrors.", "logout");
			
			return "login";

		} else {
			User currentUser = null;//userDao.getById(user.getId());
			 if (currentUser == null) {
		            logger.error("Unable to update. User with id {} not found.", user.getId());
		            /*return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + user.getId() + " not found."),
		                    HttpStatus.NOT_FOUND);*/
		        }
				userDao.save(user);
				/*logger.info("User succesfully updated!", "update");
				HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(ucBuilder.path("/dashboard").buildAndExpand(user.getId()).toUri());
		        return new ResponseEntity<String>(headers, HttpStatus.CREATED);*/
			return "dashboard";
		}

	}
	

	 
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String myProfile(Model model) {

		  model.addAttribute("title", "Profile" );
			return "profile";
		 

	}
	
	
	 



 
	
	
	
	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
	        //userValidator.validate(userForm, bindingResult);
 logger.info("before binding the new user.", "User");
	        if (bindingResult.hasErrors()) {
	            return "registration";
	        }


            logger.info("before saving the new user.", "User");
	        userService.save(user);
           logger.info("after saving the new user.", "User");
	        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
	        
	        return "redirect:/welcome";
	    }

	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(Model model, String error, String logout) {
	    	model.addAttribute("user", new User());
			 model.addAttribute("title", "Login" );
			 
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "login";
	    }

	    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	    public ModelAndView welcome(Model model) {
	    	//User user = getLoggedUser(); 
	    	ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("email", "karjule128@gmail.com");
			 modelAndView.setViewName("dashboard");
	        return modelAndView;
	    }
	
	    
	    public User getLoggedUser() {
	        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    }
	
}