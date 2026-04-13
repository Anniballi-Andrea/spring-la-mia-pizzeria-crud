package org.lessons.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Il nome non va lasciato vuoto")
    @NotBlank(message = "Il nome non va lasciato vuoto")
    private String name;

    @Lob
    private String description;

    @NotBlank(message = "L'immagine va inserita")
    private String image;

    @Column(nullable = false)
    @NotNull(message = "Il prezzo non va lasciato vuoto")
    @DecimalMin(value = "0.00", inclusive = false, message = "Il prezzo deve essere maggiore di 0")
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<Offer> offers;

    @ManyToMany
    @JoinTable(name = "ingridient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingridient_id"))
    private List<Ingridient> ingridients;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Offer> getOffers() {
        return this.offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Ingridient> getIngridients() {
        return this.ingridients;
    }

    public void setIngridients(List<Ingridient> ingridients) {
        this.ingridients = ingridients;
    }

    @Override

    public String toString() {
        return String.format("%s %s %s %s %s", name, description, image, price.toPlainString(), ingridients);
    }

}
