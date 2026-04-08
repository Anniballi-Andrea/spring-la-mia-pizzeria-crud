package org.lessons.spring_la_mia_pizzeria_crud.controller;

import org.lessons.spring_la_mia_pizzeria_crud.model.Ingridient;
import org.lessons.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.spring_la_mia_pizzeria_crud.repository.IngridientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingridients")
public class IngridientsController {

    @Autowired
    private IngridientRepository ingridientRepo;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("ingridients", ingridientRepo.findAll());
        return "ingridients/index";
    }

    @GetMapping("/create")
    public String addPizza(Model model) {
        model.addAttribute("ingridient", new Ingridient());

        return "ingridients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Ingridient formIngridient,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingridients/create-or-edit";
        }
        ingridientRepo.save(formIngridient);
        return "redirect:/ingridients";
    }

}
