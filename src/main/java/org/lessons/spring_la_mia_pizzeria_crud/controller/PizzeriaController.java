package org.lessons.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
