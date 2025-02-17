package com.geppetto.MediRecords.controller;

import com.geppetto.MediRecords.dto.AppointmentDto;
import com.geppetto.MediRecords.service.AppointmentService;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
* REST controller for managing Appointment
* Provides endpoints to create, update, delete, retrieve, and search for Appointment,
* as well as to handle file uploads and downloads associated with Appointment.
*/
@RestController
@RequestMapping("/patientdetails/appointment")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService; 


    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        log.info("Enter into createAppointment method");
        ResponseEntity<AppointmentDto> response =  ResponseEntity.status(HttpStatus.OK).body(appointmentService.createAppointment(appointmentDto));
        log.info("Exit from createAppointment method");
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable String id) {
        log.info("Enter into getAppointmentById method");
        ResponseEntity<AppointmentDto> response =ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentById(id));
        log.info("Exit from getAppointmentById method");
        return response;
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentDto>> getAllAppointment(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size) {
        log.info("Enter into getAllAppointment method");
        Page<AppointmentDto>appointmentDtoPage = appointmentService.getAllAppointment(page, size);
        log.info("Exit from getAllAppointment method");
        return new ResponseEntity<>(appointmentDtoPage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppointmentDto>> searchAppointment(@RequestParam Map<String, String> allParams) {
        log.info("Enter into searchAppointment method");
        ResponseEntity<List<AppointmentDto>> response = ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchAppointment(allParams));
        log.info("Exit from searchAppointment method");
        return response;
    }

    @GetMapping("/searchUpdate")
    public ResponseEntity<AppointmentDto> searchForUpdateAppointment(@RequestBody AppointmentDto appointmentDto) {
        log.info("Enter into searchForUpdateAppointment method");
        ResponseEntity<AppointmentDto> response = ResponseEntity.status(HttpStatus.OK).body(appointmentService.updateAppointment(appointmentDto));
        log.info("Exit from searchForUpdateAppointment method");
        return response;
    }

    @PutMapping
    public ResponseEntity<AppointmentDto> updateAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        log.info("Enter into updateAppointment method");
        ResponseEntity<AppointmentDto> response = ResponseEntity.status(HttpStatus.OK).body(appointmentService.updateAppointment(appointmentDto));
        log.info("Exit from updateAppointment method");
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable String id) {
        log.info("Enter into deleteAppointment method");
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(appointmentService.deleteAppointment(id));
        log.info("Exit from deleteAppointment method");
        return response;
    }

}
