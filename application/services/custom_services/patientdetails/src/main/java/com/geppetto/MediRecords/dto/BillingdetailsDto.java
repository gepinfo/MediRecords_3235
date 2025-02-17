package com.geppetto.MediRecords.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingdetailsDto {

    @NotBlank(message = "id cannot be null or empty")
    private String id;
     
    @NotNull(message = "billingid cannot be null or empty")  
    private int billingid;
   
    @NotNull(message = "patientid cannot be null or empty")  
    private int patientid;
   
    @NotBlank(message = "paymentstatus cannot be null or empty")  
    private String paymentstatus;
  
}
