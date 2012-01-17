package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.repository.CustomerRepository;
import com.apress.prospringmvc.bookstore.repository.OrderRepository;

@Controller
@RequestMapping("/customer/account")
public class AccountController extends AbstractCustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute
    public Customer formObject(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        return this.customerRepository.findById(customer.getId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("orders", this.orderRepository.findByCustomer(customer));
        return "customer/account";
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String update(@ModelAttribute Customer customer, HttpSession session) {
        this.customerRepository.save(customer);
        session.setAttribute("customer", customer); //Update customer in session
        return "redirect:/custome/account";
    }

}
