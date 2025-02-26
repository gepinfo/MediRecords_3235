package com.geppetto.MediRecords.service.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.geppetto.MediRecords.dao.AppointmentDao;
import com.geppetto.MediRecords.dto.AppointmentDto;
import com.geppetto.MediRecords.exception.EntityNotFoundException;
import com.geppetto.MediRecords.model.Appointment;
import com.geppetto.MediRecords.repository.AppointmentRepository;
import com.geppetto.MediRecords.service.AppointmentService;
import com.geppetto.MediRecords.util.PatientDetailsUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the {@link AppointmentService} interface. Provides services
 * related to Appointment, including CRUD operations and file uploads/downloads.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    /**
     * Constructs a {@code AppointmentServiceImpl} with the specified DAO.
     *
     * @param appointmentDao The DAO for accessing the data.
     */
    private final AppointmentDao appointmentDao;
    private final AppointmentRepository appointmentRepository;
    private final PatientDetailsUtil patientDetailsUtil;

    /**
     * Creates new appointment.
     *
     * @param appointmentDto The {@link AppointmentDto} to be created.
     * @return The created {@link AppointmentDto}.
     */
    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        log.info("Entering createAppointment method");

        Appointment appointment = patientDetailsUtil.toEntity(appointmentDto);
        Appointment createdAppointment = appointmentDao.createAppointment(appointment);
        appointmentDto = patientDetailsUtil.toDto(createdAppointment);
        log.info("Exiting createAppointment method");
        return appointmentDto;
    }

    /**
     * Retrieves appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve. Must not be
     * {@code null}.
     * @return The appointment data transfer object associated with the
     * specified ID.
     * @throws EntityNotFoundException If no appointment with the specified ID
     * is found.
     */
    @Override
    public AppointmentDto getAppointmentById(String id) {
        log.info("Entering getAppointmentById method for ID: {}", id);
        return appointmentDao.getAppointmentById(id)
                .map(appointment -> {
                    AppointmentDto dto = patientDetailsUtil.toDto(appointment);
                    log.info("Exiting getAppointmentById method for ID: {}", id);
                    return dto;
                })
                .orElseThrow(() -> {
                    log.warn("No appointment found for ID: {}", id);
                    return new EntityNotFoundException("Data not found for ID: " + id);
                });
    }

    /**
     * Retrieves all appointment.
     *
     * @return A list of {@link AppointmentDto} representing all appointment.
     */
    @Override
    public Page<AppointmentDto> getAllAppointment(int page, int size) {
        log.info("Entering getAllAppointment method");
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> appointmentPage = appointmentDao.getAllAppointment(pageable);
        Page<AppointmentDto> appointmentDtoPage = appointmentPage.map(appointment -> {
            AppointmentDto dto = patientDetailsUtil.toDto(appointment);
            return dto;
        });
        log.info("Exiting getAllAppointment method");
        return appointmentDtoPage;
    }

    /**
     * Searches for appointment based on provided parameters.
     *
     * @param allParams A map of search parameters.
     * @return A list of {@link AppointmentDto} matching the search parameters.
     */
    @Override
    public List<AppointmentDto> searchAppointment(Map<String, String> allParams) {
        log.info("Entering searchAppointment method for SQL");

        Specification<Appointment> specification = patientDetailsUtil.constructSearchQuery(allParams, Appointment.class);
        List<Appointment> results = appointmentRepository.findAll(specification);
        List<AppointmentDto> appointmentDtos = results.stream()
                .map(appointment -> {
                    AppointmentDto dto = patientDetailsUtil.toDto(appointment);
                    return dto;
                })
                .collect(Collectors.toList());

        log.info("Exiting searchAppointment method for SQL. Results found: {}", appointmentDtos.size());
        return appointmentDtos;
    }

    /**
     * Updates existing appointment.
     *
     * @param appointmentDto The {@link AppointmentDto} containing updated
     * information.
     * @return The updated {@link AppointmentDto}.
     * @throws EntityNotFoundException If no appointment with the specified ID
     * is found.
     */
    @Override
    public AppointmentDto updateAppointment(AppointmentDto appointmentDto) {
        log.info("Entering updateAppointment method for ID: {}", appointmentDto.getId());
        return appointmentDao.getAppointmentById(appointmentDto.getId())
                .map(existingAppointment -> {
                    existingAppointment = patientDetailsUtil.toEntity(appointmentDto);
                    Appointment updatedAppointment = appointmentDao.createAppointment(existingAppointment);
                    AppointmentDto responseDto = patientDetailsUtil.toDto(updatedAppointment);
                    log.info("Exiting updateAppointment method for ID: {}", appointmentDto.getId());
                    return responseDto;
                })
                .orElseThrow(() -> {
                    log.warn("No appointment found for update with ID: {}", appointmentDto.getId());
                    return new EntityNotFoundException("Data not found for update with ID: " + appointmentDto.getId());
                });
    }

    /**
     * Deletes appointment by ID.
     *
     * @param id The ID of the appointment to delete.
     * @return A message indicating the result of the deletion.
     * @throws EntityNotFoundException If no appointment with the specified ID
     * is found.
     */
    @Override
    public String deleteAppointment(String id) {
        log.info("Entering deleteAppointment method for ID: {}", id);

        appointmentDao.getAppointmentById(id)
                .orElseThrow(() -> {
                    log.warn("No appointment found with ID: {}. Deletion failed.", id);
                    return new EntityNotFoundException("No appointment found with ID: " + id + ". Unable to delete.");
                });

        appointmentDao.deleteAppointment(id);
        log.info("Successfully deleted Appointment with ID: {}", id);

        return "Appointment deleted successfully";
    }

}
