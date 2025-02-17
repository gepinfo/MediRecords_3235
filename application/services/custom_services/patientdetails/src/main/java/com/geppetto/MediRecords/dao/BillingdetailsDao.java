package com.geppetto.MediRecords.dao;

import com.geppetto.MediRecords.repository.BillingdetailsRepository;

import com.geppetto.MediRecords.model.Billingdetails;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
* Implementation of the {@link BillingdetailsDao} interface.
* Provides methods to interact with the {@link BillingdetailsRepository} for CRUD operations on {@link Billingdetails } entities.
*/
@Service
public class BillingdetailsDao {

    private final BillingdetailsRepository billingdetailsRepository;
    /**
     * Constructs a new {@code BillingdetailsDao} with the specified repository.
     *
     * @param billingdetailsRepository The repository used for accessing {@link Billingdetails} entities. Must not be {@code null}.
     */
    public BillingdetailsDao(BillingdetailsRepository billingdetailsRepository) {
        this.billingdetailsRepository = billingdetailsRepository;
    }

    /**
     * Creates new billingdetails.
     *
     * @param billingdetails The {@link Billingdetails} entity to create. Must not be {@code null}.
     * @return The created {@link Billingdetails} entity.
     */
    public Billingdetails createBillingdetails(Billingdetails billingdetails) {
        return billingdetailsRepository.save(billingdetails);
    }


    /**
     * Retrieves billingdetails by its ID.
     *
     * @param id The ID of the billingdetails to retrieve. Must not be {@code null}.
     * @return An {@link Optional} containing the billingdetails if found, or an empty {@code Optional} if not.
     */
    public Optional<Billingdetails> getBillingdetailsById(String id) {
        return billingdetailsRepository.findById(id);
    }


     /**
     * Retrieves all billingdetails from the repository.
     *
     * @return A list of all {@link Billingdetails} entities.
     */
    public Page<Billingdetails> getAllBillingdetails(Pageable pageable) {
        return billingdetailsRepository.findAll(pageable);
    }


    /**
     * Retrieves billingdetails by its ID for update purposes.
     *
     * @param id The ID of the billingdetails to retrieve. Must not be {@code null}.
     * @return An {@link Optional} containing the billingdetails if found, or an empty {@code Optional} if not.
     */
    public Optional<Billingdetails> updateBillingdetails(String id) {
        return billingdetailsRepository.findById(id);
    }


    /**
     * Deletes billingdetails by its ID.
     *
     * @param id The ID of the billingdetails to delete. Must not be {@code null}.
     */
    public void deleteBillingdetails(String id) {
        billingdetailsRepository.deleteById(id);
    }


}


