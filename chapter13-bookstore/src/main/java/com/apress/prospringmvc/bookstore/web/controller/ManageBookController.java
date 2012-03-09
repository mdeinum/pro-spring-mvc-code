package com.apress.prospringmvc.bookstore.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.support.BookBuilder;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

@Controller
public class ManageBookController {

	@Autowired
	private BookstoreService bookstoreService;

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("manageBookForm")
	public ManageBookForm getManageBookForm(ManageBookForm manageBookForm) {
		manageBookForm.setSelectableCategories(categoryService.findAll());
		return manageBookForm;
	}

	@RequestMapping("secured/manageBooks/manageBooks.htm")
	public ModelAndView manageBooks(@ModelAttribute
	ManageBookForm manageBookForm, ModelAndView mov) {

		mov.setViewName("manageBooks");
		return mov;
	}

	@RequestMapping("secured/addBooks.htm")
	@PreAuthorize("hasRole('PERM_ADD_BOOKS')")
	public ModelAndView addBooks(@ModelAttribute
	@Valid
	ManageBookForm manageBookForm, BindingResult bindingResult, ModelAndView mov, SessionStatus status) {
		mov.setViewName("manageBooks");
		manageBookForm.setSelectableCategories(categoryService.findAll());

		if (bindingResult.hasErrors()) {
			return mov;
		}

		BookBuilder bookBuilder = new BookBuilder();
		bookBuilder.title(manageBookForm.getTitle()).description(manageBookForm.getDescription())
				.price(manageBookForm.getPrice().toString()).author(manageBookForm.getAuthor())
				.year(manageBookForm.getYear()).category(manageBookForm.getCategory());

		Book book = bookBuilder.build(true);

		bookstoreService.addBook(book);
		manageBookForm.clear();
		mov.addObject("actionSuccess", "book");

		status.setComplete();
		return mov;
	}

	@RequestMapping("secured/addCategory.htm")
	@PreAuthorize("hasRole('PERM_ADD_CATEGORIES')")
	public ModelAndView addCategory(@ModelAttribute
	@Valid
	ManageCategoryForm manageCategoryForm, BindingResult bindingResult, @ModelAttribute
	ManageBookForm manageBookForm, ModelAndView mov, Errors errors) {
		mov.setViewName("manageBooks");

		if (bindingResult.hasErrors()) {
			return mov;
		}

		categoryService.addCategory(new Category(manageCategoryForm.getCategory()));
		manageBookForm.setSelectableCategories(categoryService.findAll());
		mov.addObject("actionSuccess", "category");
		return mov;
	}
}
