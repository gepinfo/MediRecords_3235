package com.geppetto.MediRecords.dao;

import com.geppetto.MediRecords.repository.AppointmentRepository;

import com.geppetto.MediRecords.model.Appointment;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
* Implementation of the {@link AppointmentDao} interface.
* Provides methods to interact with the {@link AppointmentRepository} for CRUD operations on {@link Appointment } entities.
*/
@Service
public class AppointmentDao {

    private final AppointmentRepository appointmentRepository;
    /**
     * Constructs a new {@code AppointmentDao} with the specified repository.
     *
     * @param appointmentRepository The repository used for accessing {@link Appointment} entities. Must not be {@code null}.
     */
    public AppointmentDao(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Creates new appointment.
     *
     * @param appointment The {@link Appointment} entity to create. Must not be {@code null}.
     * @return The created {@link Appointment} entity.
     */
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    /**
     * Retrieves appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve. Must not be {@code null}.
     * @return An {@link Optional} containing the appointment if found, or an empty {@code Optional} if not.
     */
    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }


     /**
     * Retrieves all appointment from the repository.
     *
     * @return A list of all {@link Appointment} entities.
     */
    public Page<Appointment> getAllAppointment(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }


    /**
     * Retrieves appointment by its ID for update purposes.
     *
     * @param id The ID of the appointment to retrieve. Must not be {@code null}.
     * @return An {@link Optional} containing the appointment if found, or an empty {@code Optional} if not.
     */
    public Optional<Appointment> updateAppointment(String id) {
        return appointmentRepository.findById(id);
    }


    /**
     * Deletes appointment by its ID.
     *
     * @param id The ID of the appointment to delete. Must not be {@code null}.
     */
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }


}


