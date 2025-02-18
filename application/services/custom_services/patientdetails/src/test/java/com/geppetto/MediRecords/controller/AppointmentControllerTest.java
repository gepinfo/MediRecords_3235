package com.geppetto.MediRecords.controller;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geppetto.MediRecords.dto.AppointmentDto;
import com.geppetto.MediRecords.service.AppointmentService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    private AppointmentDto appointmentDto;

    @BeforeEach
    void setUp() {
        appointmentDto = new AppointmentDto("1", 101, 201, "Dr. Smith");
    }

    @Test
    void testCreateAppointment() throws Exception {
        when(appointmentService.createAppointment(any(AppointmentDto.class))).thenReturn(appointmentDto);

        mockMvc.perform(post("/patientdetails/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(appointmentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.appointmentid").value(101))
                .andExpect(jsonPath("$.patientid").value(201))
                .andExpect(jsonPath("$.doctorname").value("Dr. Smith"));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        when(appointmentService.getAppointmentById("1")).thenReturn(appointmentDto);

        mockMvc.perform(get("/patientdetails/appointment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.appointmentid").value(101))
                .andExpect(jsonPath("$.patientid").value(201))
                .andExpect(jsonPath("$.doctorname").value("Dr. Smith"));
    }

    @Test
    void testGetAllAppointments() throws Exception {
        Page<AppointmentDto> page = new PageImpl<>(List.of(appointmentDto));
        when(appointmentService.getAllAppointment(0, 3)).thenReturn(page);

        mockMvc.perform(get("/patientdetails/appointment?page=0&size=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value("1"))
                .andExpect(jsonPath("$.content[0].appointmentid").value(101))
                .andExpect(jsonPath("$.content[0].patientid").value(201))
                .andExpect(jsonPath("$.content[0].doctorname").value("Dr. Smith"));
    }

    @Test
    void testSearchAppointment() throws Exception {
        List<AppointmentDto> list = List.of(appointmentDto);
        when(appointmentService.searchAppointment(any(Map.class))).thenReturn(list);

        mockMvc.perform(get("/patientdetails/appointment/search").param("doctorname", "Dr. Smith"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].appointmentid").value(101))
                .andExpect(jsonPath("$[0].patientid").value(201))
                .andExpect(jsonPath("$[0].doctorname").value("Dr. Smith"));
    }

    @Test
    void testSearchForUpdateAppointment() throws Exception {
        when(appointmentService.updateAppointment(any(AppointmentDto.class))).thenReturn(appointmentDto);

        mockMvc.perform(get("/patientdetails/appointment/searchUpdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(appointmentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.appointmentid").value(101))
                .andExpect(jsonPath("$.patientid").value(201))
                .andExpect(jsonPath("$.doctorname").value("Dr. Smith"));
    }

    @Test
    void testUpdateAppointment() throws Exception {
        when(appointmentService.updateAppointment(any(AppointmentDto.class))).thenReturn(appointmentDto);

        mockMvc.perform(put("/patientdetails/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(appointmentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.appointmentid").value(101))
                .andExpect(jsonPath("$.patientid").value(201))
                .andExpect(jsonPath("$.doctorname").value("Dr. Smith"));
    }

    @Test
    void testDeleteAppointment() throws Exception {
        when(appointmentService.deleteAppointment("1")).thenReturn("Appointment deleted successfully");

        mockMvc.perform(delete("/patientdetails/appointment/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Appointment deleted successfully"));
    }
}
