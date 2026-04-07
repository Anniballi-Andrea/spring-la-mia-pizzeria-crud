package org.lessons.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Column(name = "name")
    @NotNull(message = "il nome non può essere vuoto")
    @NotBlank
    private String offerName;

    @NotNull(message = "la data di inizio non può essere vuota")
    private LocalDate start;

    @NotNull(message = "la data di fine non può essere vuota")
    private LocalDate end;

}
