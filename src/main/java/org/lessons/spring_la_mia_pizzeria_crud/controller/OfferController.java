package org.lessons.spring_la_mia_pizzeria_crud.controller;

import org.lessons.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferRepository repo;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "offer/addOffer";
        }

        repo.save(formOffer);

        return "redirect:/pizzeria";
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", repo.findById(id).get());
        return "offer/editOffer";
    }

    @PostMapping("/edit/{id}")
    public String updatePizza(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult,
            Model Model) {
        if (bindingResult.hasErrors()) {
            return "offer/edit";
        }
        repo.save(formOffer);

        return "redirect:/pizzeria";
    }
}
