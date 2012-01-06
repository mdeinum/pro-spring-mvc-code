package com.apress.prospringmvc.pizzarus.web;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.OrderLine;
import com.apress.prospringmvc.pizzarus.service.OrderService;
import com.apress.prospringmvc.pizzarus.service.PizzaService;

@Controller
@RequestMapping(value = "/order.htm")
@SessionAttributes("newOrder")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PizzaService pizzaService;

    @ModelAttribute
    public void referenceData(final Model model, final Locale currentLocale) {
        model.addAttribute(this.pizzaService.findAll());
        Map<String, String> countries = new TreeMap<String, String>();
        for (Locale locale : Locale.getAvailableLocales()) {
            countries.put(locale.getCountry(), locale.getDisplayCountry(currentLocale));
        }
        model.addAttribute("countries", countries);
    }

    @ModelAttribute("newOrder")
    public Order newOrder(final Locale currentLocale) {
        Order order = new Order();
        order.setCountry(currentLocale.getCountry());
        return order;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void index() {
    }

    @RequestMapping(method = RequestMethod.POST, params = "add-line")
    public String addLine(@ModelAttribute("newOrder") final Order newOrder) {
        newOrder.addOrderLine(new OrderLine());
        return "redirect:/order.htm";
    }

    @RequestMapping(method = RequestMethod.POST, params = "calculate")
    public String calculate(@ModelAttribute("newOrder") final Order newOrder) {
        newOrder.removeEmptyOrderLines();
        return "redirect:/order.htm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newOrder(final @Valid @ModelAttribute("newOrder") Order newOrder, final BindingResult result,
            final SessionStatus sessionStatus, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            //handle error
            return "order";
        } else {
            this.orderService.newOrder(newOrder);
            sessionStatus.setComplete();
            redirectAttributes.addFlashAttribute("orderNumber", newOrder.getId());
            return "redirect:/order-success.htm";
        }
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields("id", "date");
    }

}
