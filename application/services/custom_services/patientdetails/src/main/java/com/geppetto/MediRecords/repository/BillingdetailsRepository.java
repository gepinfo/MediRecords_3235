package com.geppetto.MediRecords.repository;

import com.geppetto.MediRecords.model.Billingdetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface BillingdetailsRepository extends JpaRepository<Billingdetails, String> , JpaSpecificationExecutor<Billingdetails> {
    
    Page<Billingdetails> findAll(Pageable pageable);
    
}