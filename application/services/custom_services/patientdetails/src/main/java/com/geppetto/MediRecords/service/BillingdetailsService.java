package com.geppetto.MediRecords.service;

import java.util.List;
import com.geppetto.MediRecords.dto.BillingdetailsDto;
import java.util.Map;
// import com.geppetto.MediRecords.dto.BillingdetailsDto;
import org.springframework.data.domain.Page;
// import com.geppetto.MediRecords.dto.BillingdetailsDto;

public interface BillingdetailsService {

    BillingdetailsDto createBillingdetails(BillingdetailsDto billingdetailsDto);

    BillingdetailsDto getBillingdetailsById(String id);

    Page<BillingdetailsDto> getAllBillingdetails(int page, int size);

    List<BillingdetailsDto> searchBillingdetails(Map<String, String> allParams);

    BillingdetailsDto updateBillingdetails(BillingdetailsDto billingdetailsDto);

    String deleteBillingdetails(String id);

}
