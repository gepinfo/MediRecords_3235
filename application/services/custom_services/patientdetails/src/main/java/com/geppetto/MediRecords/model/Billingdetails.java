package com.geppetto.MediRecords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name ="Billingdetails")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Billingdetails {

    @Id
    private String id;
    
    private int billingid;
    
    private int patientid;
    
    private String paymentstatus;
    
    
    
}