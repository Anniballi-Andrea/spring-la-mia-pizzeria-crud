package org.lessons.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepo;

    public List<Pizza> findAll() {

        return pizzaRepo.findAll();
    }

    public List<Pizza> findAllSortByName() {

        return pizzaRepo.findAll(Sort.by("name"));
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaRepo.findById(id);

        if (pizzaAttempt.isEmpty()) {

        }
        return pizzaAttempt.get();
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepo.save(pizza);
    }

    public Pizza update(Pizza pizza) {

        return pizzaRepo.save(pizza);
    }

    public void delete(Pizza pizza) {

        pizzaRepo.delete(pizza);

    }

    public void deleteById(Integer id) {
        Pizza pizza = getById(id);

        pizzaRepo.delete(pizza);

    }

    public List<Pizza> findByName(String name) {
        List<Pizza> pizzas = pizzaRepo.findByNameContaining(name);

        return pizzas;
    }

}
