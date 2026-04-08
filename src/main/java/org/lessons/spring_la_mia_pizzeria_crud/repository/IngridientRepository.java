package org.lessons.spring_la_mia_pizzeria_crud.repository;

import org.lessons.spring_la_mia_pizzeria_crud.model.Ingridient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngridientRepository extends JpaRepository<Ingridient, Integer> {

}