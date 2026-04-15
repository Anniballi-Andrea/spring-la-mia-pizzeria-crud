package org.lessons.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.spring_la_mia_pizzeria_crud.service.IngridientsService;
import org.lessons.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzeria")
public class PizzeriaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngridientsService ingridientService;

    @GetMapping
    public String home(Authentication authentication, Model model) {
        List<Pizza> pizzas = pizzaService.getAll();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("username", authentication.getName());
        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaService.getById(id);
        model.addAttribute("pizza", pizza);

        return "pizza/show";
    }

    @GetMapping("/searchByName")
    public String searchName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = pizzaService.findByName(name);

        model.addAttribute("pizzas", pizzas);

        return "pizza/index";
    }

    @GetMapping("/create")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingridients", ingridientService.getAll());
        return "pizza/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingridients", ingridientService.getAll());
            return "pizza/create-or-edit";
        }
        pizzaService.create(formPizza);
        return "redirect:/pizzeria";
    }

    @GetMapping("/edit/{id}")
    public String editPizza(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingridients", ingridientService.getAll());
        model.addAttribute("edit", true);
        return "pizza/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingridients", ingridientService.getAll());
            model.addAttribute("edit", true);
            return "pizza/create-or-edit";
        }
        pizzaService.update(formPizza);

        return "redirect:/pizzeria";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        pizzaService.deleteById(id);

        return "redirect:/pizzeria";
    }

    @GetMapping("/{id}/offer")
    public String Offer(@PathVariable Integer id, Model model) {

        Offer offer = new Offer();
        offer.setPizza(pizzaService.getById(id));

        model.addAttribute("offer", offer);

        return "offer/add-offer";

    }

}
