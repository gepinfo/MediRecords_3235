package com.geppetto.MediRecords.controller;

import com.geppetto.MediRecords.dto.BillingdetailsDto;
import com.geppetto.MediRecords.service.BillingdetailsService;
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
* REST controller for managing Billingdetails
* Provides endpoints to create, update, delete, retrieve, and search for Billingdetails,
* as well as to handle file uploads and downloads associated with Billingdetails.
*/
@RestController
@RequestMapping("/patientdetails/billingdetails")
@RequiredArgsConstructor
@Slf4j
public class BillingdetailsController {

    private final BillingdetailsService billingdetailsService; 


    @PostMapping
    public ResponseEntity<BillingdetailsDto> createBillingdetails(@Valid @RequestBody BillingdetailsDto billingdetailsDto) {
        log.info("Enter into createBillingdetails method");
        ResponseEntity<BillingdetailsDto> response =  ResponseEntity.status(HttpStatus.OK).body(billingdetailsService.createBillingdetails(billingdetailsDto));
        log.info("Exit from createBillingdetails method");
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingdetailsDto> getBillingdetailsById(@PathVariable String id) {
        log.info("Enter into getBillingdetailsById method");
        ResponseEntity<BillingdetailsDto> response =ResponseEntity.status(HttpStatus.OK).body(billingdetailsService.getBillingdetailsById(id));
        log.info("Exit from getBillingdetailsById method");
        return response;
    }

    @GetMapping
    public ResponseEntity<Page<BillingdetailsDto>> getAllBillingdetails(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size) {
        log.info("Enter into getAllBillingdetails method");
        Page<BillingdetailsDto>billingdetailsDtoPage = billingdetailsService.getAllBillingdetails(page, size);
        log.info("Exit from getAllBillingdetails method");
        return new ResponseEntity<>(billingdetailsDtoPage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BillingdetailsDto>> searchBillingdetails(@RequestParam Map<String, String> allParams) {
        log.info("Enter into searchBillingdetails method");
        ResponseEntity<List<BillingdetailsDto>> response = ResponseEntity.status(HttpStatus.OK).body(billingdetailsService.searchBillingdetails(allParams));
        log.info("Exit from searchBillingdetails method");
        return response;
    }

    @GetMapping("/searchUpdate")
    public ResponseEntity<BillingdetailsDto> searchForUpdateBillingdetails(@RequestBody BillingdetailsDto billingdetailsDto) {
        log.info("Enter into searchForUpdateBillingdetails method");
        ResponseEntity<BillingdetailsDto> response = ResponseEntity.status(HttpStatus.OK).body(billingdetailsService.updateBillingdetails(billingdetailsDto));
        log.info("Exit from searchForUpdateBillingdetails method");
        return response;
    }

    @PutMapping
    public ResponseEntity<BillingdetailsDto> updateBillingdetails(@Valid @RequestBody BillingdetailsDto billingdetailsDto) {
        log.info("Enter into updateBillingdetails method");
        ResponseEntity<BillingdetailsDto> response = ResponseEntity.status(HttpStatus.OK).body(billingdetailsService.updateBillingdetails(billingdetailsDto));
        log.info("Exit from updateBillingdetails method");
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBillingdetails(@PathVariable String id) {
        log.info("Enter into deleteBillingdetails method");
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(billingdetailsService.deleteBillingdetails(id));
        log.info("Exit from deleteBillingdetails method");
        return response;
    }

}
