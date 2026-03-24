package org.lessons.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PizzaRepository repo;

    @GetMapping
    public String home(Model model) {
        List<Pizza> pizzas = repo.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);

        return "pizza/show";
    }

    @GetMapping("/searchByName")
    public String searchName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = repo.findByNameContaining(name);

        model.addAttribute("pizzas", pizzas);

        return "pizza/index";
    }

    @GetMapping("/create")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizza/addPizza";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizza/addPizza";
        }
        repo.save(formPizza);
        return "redirect:/pizzeria";
    }

    @GetMapping("/edit/{id}")
    public String editPizza(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repo.findById(id).get());
        return "pizza/editPizza";
    }

    @PostMapping("/edit/{id}")
    public String updatePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            Model Model) {
        if (bindingResult.hasErrors()) {
            return "pizza/edit";
        }
        repo.save(formPizza);

        return "redirect:/pizzeria";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/pizzeria";
    }

}
