package org.lessons.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertService {

    @Autowired
    private OfferRepository offerRepo;

    public List<Offer> getAll() {
        return offerRepo.findAll();
    }

    public Offer getById(Integer id) {
        Optional<Offer> offerAttempt = offerRepo.findById(id);
        if (offerAttempt.isEmpty()) {

        }
        return offerAttempt.get();
    }

    public Offer create(Offer offer) {
        return offerRepo.save(offer);
    }

    public Offer update(Offer offer) {
        return offerRepo.save(offer);
    }

    public void delete(Offer offer) {

        offerRepo.delete(offer);

    }

    public void deleteById(Integer id) {
        Offer offer = getById(id);

        offerRepo.delete(offer);

    }

}
