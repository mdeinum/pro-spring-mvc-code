package com.apress.prospringmvc.pizzarus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.service.InvalidCredentialsException;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

@Controller
public class AuthenticationController {

	public static final String AUTHENTICATED_CUSTOMER_KEY = "authenticatedCustomer";

	private static final String LOGIN_FAILED_KEY = "label.login.failed";

	@Autowired
	private PizzasService pizzasService;

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
		} catch (InvalidCredentialsException invalidCredentialsException) {
			errors.reject(LOGIN_FAILED_KEY);
			mov.setViewName("login");
		}

		return mov;
	}

	public AuthenticationForm initializeForm() {
		return new AuthenticationForm();
	}

	public Event authenticate(AuthenticationForm authenticationForm, MvcExternalContext externalContext) {
		try {
			authenticate(authenticationForm, ((HttpServletRequest) externalContext.getNativeRequest()).getSession());
		} catch (InvalidCredentialsException invalidCredentialsException) {
			return new EventFactorySupport().error(this);
		}
		return new EventFactorySupport().success(this);
	}

	private void authenticate(AuthenticationForm authenticationForm, HttpSession httpSession)
			throws InvalidCredentialsException {
		Customer customer = pizzasService.authenticateCustomer(authenticationForm.getUsername(),
				authenticationForm.getPassword());
		httpSession.setAttribute(AUTHENTICATED_CUSTOMER_KEY, customer);
	}
}
