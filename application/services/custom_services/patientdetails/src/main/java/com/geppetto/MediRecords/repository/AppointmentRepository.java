package com.geppetto.MediRecords.repository;

import com.geppetto.MediRecords.model.Appointment;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> , JpaSpecificationExecutor<Appointment> {
    
    Page<Appointment> findAll(Pageable pageable);
    
}