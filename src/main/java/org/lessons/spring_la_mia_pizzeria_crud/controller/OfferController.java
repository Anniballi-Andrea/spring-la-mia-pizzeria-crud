package org.lessons.spring_la_mia_pizzeria_crud.controller;

import org.lessons.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.spring_la_mia_pizzeria_crud.service.OffertService;
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
    private OffertService offertService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "offer/add-offer";
        }

        offertService.create(formOffer);

        return "redirect:/pizzeria";
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", offertService.getById(id));
        return "offer/edit-offer";
    }

    @PostMapping("/edit/{id}")
    public String updateOffer(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult,
            Model Model) {
        if (bindingResult.hasErrors()) {
            return "offer/edit-offer";
        }
        offertService.update(formOffer);

        return "redirect:/pizzeria";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        offertService.deleteById(id);

        return "redirect:/pizzeria";
    }

}
