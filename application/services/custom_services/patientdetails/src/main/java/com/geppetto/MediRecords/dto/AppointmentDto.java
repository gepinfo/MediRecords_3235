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
public class AppointmentDto {

    @NotBlank(message = "id cannot be null or empty")
    private String id;
     
    @NotNull(message = "appointmentid cannot be null or empty")  
    private int appointmentid;
   
    @NotNull(message = "patientid cannot be null or empty")  
    private int patientid;
   
    @NotBlank(message = "doctorname cannot be null or empty")  
    private String doctorname;
  
}
