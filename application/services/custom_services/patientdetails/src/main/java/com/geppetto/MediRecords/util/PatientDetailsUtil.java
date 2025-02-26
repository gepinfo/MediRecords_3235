package com.geppetto.MediRecords.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.geppetto.MediRecords.dto.AppointmentDto;
import com.geppetto.MediRecords.dto.BillingdetailsDto;
import com.geppetto.MediRecords.model.Appointment;
import com.geppetto.MediRecords.model.Billingdetails;

import jakarta.persistence.criteria.Predicate;

@Component
public class PatientDetailsUtil {

    /**
    * Constructs a JPA Specification query for dynamic search based on the
    * given parameters.
    *
    * @param allParams A map containing query parameters (field name as key,
    * search value as value).
    * @return A JPA Specification that can be used to filter results
    * dynamically.
    */
    public <T> Specification<T> constructSearchQuery(Map<String, String> allParams, Class<T> entityClass) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            allParams.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    try {
                        Class<?> fieldType = entityClass.getDeclaredField(key).getType();

                        if (fieldType.equals(String.class)) {
                            predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                        } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(key), Integer.parseInt(value)));
                        } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(key), Long.parseLong(value)));
                        } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(key), Boolean.parseBoolean(value)));
                        } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(key), Double.parseDouble(value)));
                        } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(key), Float.parseFloat(value)));
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(key), value));
                        }
                    } catch (NoSuchFieldException e) {
                        System.out.print("NoSuchFieldException:" + e);
                    }
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Appointment toEntity(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .id(appointmentDto.getId())
                .appointmentid(appointmentDto.getAppointmentid())
                .patientid(appointmentDto.getPatientid())
                .doctorname(appointmentDto.getDoctorname())
                .build();
    }

    public AppointmentDto toDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .appointmentid(appointment.getAppointmentid())
                .patientid(appointment.getPatientid())
                .doctorname(appointment.getDoctorname())
                .build();
    }

    public Billingdetails toEntity(BillingdetailsDto billingdetailsDto) {
        return Billingdetails.builder()
                .id(billingdetailsDto.getId())
                .billingid(billingdetailsDto.getBillingid())
                .patientid(billingdetailsDto.getPatientid())
                .paymentstatus(billingdetailsDto.getPaymentstatus())
                .build();
    }

    public BillingdetailsDto toDto(Billingdetails billingdetails) {
        return BillingdetailsDto.builder()
                .id(billingdetails.getId())
                .billingid(billingdetails.getBillingid())
                .patientid(billingdetails.getPatientid())
                .paymentstatus(billingdetails.getPaymentstatus())
                .build();
    }

}
