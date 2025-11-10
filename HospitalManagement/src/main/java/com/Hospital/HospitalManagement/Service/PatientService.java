package com.Hospital.HospitalManagement.Service;

import com.Hospital.HospitalManagement.Entity.Patient;
import com.Hospital.HospitalManagement.Repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Create new patient
    public Patient createPatient(Patient patient) {
        if(patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Patient with email " + patient.getEmail() + " already exists.");
        }
        return patientRepository.save(patient);
    }

    // Get patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    // Get patient by email
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found with email " + email));
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Update patient
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient patient = getPatientById(id);

        if(updatedPatient.getName() != null) patient.setName(updatedPatient.getName());
        if(updatedPatient.getBirthDate() != null) patient.setBirthDate(updatedPatient.getBirthDate());
        if(updatedPatient.getEmail() != null && !updatedPatient.getEmail().equals(patient.getEmail())) {
            if(patientRepository.existsByEmail(updatedPatient.getEmail())) {
                throw new RuntimeException("Patient with email " + updatedPatient.getEmail() + " already exists.");
            }
            patient.setEmail(updatedPatient.getEmail());
        }
        if(updatedPatient.getGender() != null) patient.setGender(updatedPatient.getGender());
        if(updatedPatient.getBloodGroup() != null) patient.setBloodGroup(updatedPatient.getBloodGroup());
        if(updatedPatient.getInsurance() != null) patient.setInsurance(updatedPatient.getInsurance());
        if(updatedPatient.getAppointments() != null) patient.setAppointments(updatedPatient.getAppointments());

        return patientRepository.save(patient);
    }

    // Delete patient
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }
}
