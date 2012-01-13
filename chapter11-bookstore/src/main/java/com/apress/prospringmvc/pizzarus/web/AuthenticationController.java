package com.apress.prospringmvc.pizzarus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.service.CustomerService;

/**
 * This controller talks to the {@link PizzasService} to authenticate a user. This controller can be used via Spring MVC
 * (request mapping login.html) or as POJO for example via Web Flow
 * 
 * @author Koen Serneels
 */

@Controller
public class AuthenticationController {

	public static final String AUTHENTICATED_CUSTOMER_KEY = "authenticatedCustomer";

	private static final String LOGIN_FAILED_KEY = "label.login.failed";

	@Autowired
	private CustomerService customerService;

	// ----- Spring MVC logic

	@RequestMapping("login.html")
	public ModelAndView authentication() {
		ModelAndView mov = new ModelAndView();

		mov.setViewName("login");
		mov.addObject("authenticationForm", initializeForm());
		return mov;
	}

	@RequestMapping(value = "authenticate.html", method = RequestMethod.POST)
	public ModelAndView authentication(@ModelAttribute
	AuthenticationForm authenticationForm, Errors errors, ModelAndView mov, HttpSession httpSession) {
		try {
			authenticate(authenticationForm, httpSession);
			mov.addObject("authenticationOk", "true");
			mov.addObject("username", authenticationForm.getUsername());
			mov.setViewName("main");
		} catch (AuthenticationException authenticationException) {
			errors.reject(LOGIN_FAILED_KEY);
			mov.setViewName("login");
		}

		return mov;
	}

	public AuthenticationForm initializeForm() {
		return new AuthenticationForm();
	}

	// ---- POJO logic
	public Event authenticate(AuthenticationForm authenticationForm, MvcExternalContext externalContext,
			MessageContext messageContext) {
		try {
			authenticate(authenticationForm, ((HttpServletRequest) externalContext.getNativeRequest()).getSession());
		} catch (AuthenticationException authenticationException) {
			messageContext.addMessage(new MessageBuilder().error().code(LOGIN_FAILED_KEY).build());
			return new EventFactorySupport().error(this);
		}
		return new EventFactorySupport().success(this);
	}

	// ---- Helpers
	private void authenticate(AuthenticationForm authenticationForm, HttpSession httpSession)
			throws AuthenticationException {
		Customer customer = customerService.login(authenticationForm.getUsername(), authenticationForm.getPassword());
		httpSession.setAttribute(AUTHENTICATED_CUSTOMER_KEY, customer);
	}
}
