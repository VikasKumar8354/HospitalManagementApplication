package com.Hospital.HospitalManagement.Service;

import com.Hospital.HospitalManagement.Entity.Appointment;
import com.Hospital.HospitalManagement.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }


    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = getAppointmentById(id);

        if (appointmentDetails.getAppointment() != null) {
            appointment.setAppointment(appointmentDetails.getAppointment());
        }

        if (appointmentDetails.getReason() != null) {
            appointment.setReason(appointmentDetails.getReason());
        }

        if (appointmentDetails.getPatient() != null) {
            appointment.setPatient(appointmentDetails.getPatient());
        }

        if (appointmentDetails.getDoctor() != null) {
            appointment.setDoctor(appointmentDetails.getDoctor());
        }

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }
}
