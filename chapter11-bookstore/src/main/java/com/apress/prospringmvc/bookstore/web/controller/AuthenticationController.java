package com.apress.prospringmvc.bookstore.web.controller;

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
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.service.BookstoreService;

/**
 * This controller talks to the {@link BookstoreService} to authenticate a user. This controller can be used via Spring
 * MVC (request mapping login.html) or as POJO for example via Web Flow
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Controller
public class AuthenticationController {

	public static final String AUTHENTICATED_ACCOUNT_KEY = "authenticatedAccount";

	private static final String LOGIN_FAILED_KEY = "label.login.failed";

	@Autowired
	private AccountService accountService;

	// ----- Spring MVC logic

	public AuthenticationForm initializeForm() {
		return new AuthenticationForm();
	}

	@RequestMapping("login.htm")
	public ModelAndView authentication() {
		ModelAndView mov = new ModelAndView();

		mov.setViewName("login");
		mov.addObject("authenticationForm", initializeForm());
		return mov;
	}

	@RequestMapping(value = "authenticate.htm", method = RequestMethod.POST)
	public ModelAndView authentication(@ModelAttribute
	AuthenticationForm authenticationForm, Errors errors, ModelAndView mov, HttpSession httpSession,
			HttpServletRequest httpServletRequest) {
		try {
			authenticate(authenticationForm, httpSession);
			mov.addObject("authenticationOk", "true");
			mov.addObject("username", authenticationForm.getUsername());
			mov.setViewName("main");
		} catch (AuthenticationException authenticationException) {
			errors.reject(
					LOGIN_FAILED_KEY,
					new Object[] { new RequestContext(httpServletRequest).getMessage(authenticationException.getCode()) },
					null);
			mov.setViewName("login");
		}

		return mov;
	}

	@RequestMapping(value = "authenticate.htm", params = "_eventId_previous", method = RequestMethod.POST)
	public ModelAndView previous(ModelAndView mov) {
		mov.setViewName("main");
		return mov;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.htm";
	}

	// ---- POJO logic
	public Event authenticate(AuthenticationForm authenticationForm, MvcExternalContext externalContext,
			MessageContext messageContext) {
		try {
			authenticate(authenticationForm, ((HttpServletRequest) externalContext.getNativeRequest()).getSession());
		} catch (AuthenticationException authenticationException) {
			messageContext.addMessage(new MessageBuilder().error().code(LOGIN_FAILED_KEY)
					.arg(authenticationException.getLocalizedMessage()).build());
			return new EventFactorySupport().error(this);
		}
		return new EventFactorySupport().success(this);
	}

	// ---- Helpers
	private void authenticate(AuthenticationForm authenticationForm, HttpSession httpSession)
			throws AuthenticationException {
		Account account = accountService.login(authenticationForm.getUsername(), authenticationForm.getPassword());
		httpSession.setAttribute(AUTHENTICATED_ACCOUNT_KEY, account);
	}
}
