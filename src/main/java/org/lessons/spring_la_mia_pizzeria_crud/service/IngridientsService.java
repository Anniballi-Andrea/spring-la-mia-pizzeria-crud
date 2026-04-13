package org.lessons.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.spring_la_mia_pizzeria_crud.model.Ingridient;
import org.lessons.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.spring_la_mia_pizzeria_crud.repository.IngridientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngridientsService {

    @Autowired
    private IngridientRepository ingridientRepo;

    public List<Ingridient> getAll() {
        return ingridientRepo.findAll();
    }

    public Ingridient getById(Integer id) {
        Optional<Ingridient> ingridientAttempt = ingridientRepo.findById(id);
        if (ingridientAttempt.isEmpty()) {

        }

        return ingridientAttempt.get();
    }

    public Ingridient create(Ingridient ingridient) {
        return ingridientRepo.save(ingridient);
    }

    public Ingridient update(Ingridient ingridient) {
        return ingridientRepo.save(ingridient);
    }

    public void delete(Ingridient ingridient) {

        for (Pizza linkedPizza : ingridient.getPizzas()) {

            linkedPizza.getIngridients().remove(ingridient);

        }

        ingridientRepo.delete(ingridient);

    }

    public void deleteById(Integer Id) {
        Ingridient ingridient = getById(Id);

        for (Pizza linkedPizza : ingridient.getPizzas()) {

            linkedPizza.getIngridients().remove(ingridient);

        }

        ingridientRepo.delete(ingridient);
    }

}
