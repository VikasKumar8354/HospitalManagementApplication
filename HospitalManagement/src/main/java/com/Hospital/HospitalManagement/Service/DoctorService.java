package com.Hospital.HospitalManagement.Service;

import com.Hospital.HospitalManagement.Entity.Doctor;
import com.Hospital.HospitalManagement.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        if(doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Doctor with email '" + doctor.getEmail() + "' already exists.");
        }
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);

        if(doctorDetails.getName() != null) {
            doctor.setName(doctorDetails.getName());
        }

        if(doctorDetails.getEmail() != null && !doctorDetails.getEmail().equals(doctor.getEmail())) {
            if(doctorRepository.existsByEmail(doctorDetails.getEmail())) {
                throw new RuntimeException("Doctor with email '" + doctorDetails.getEmail() + "' already exists.");
            }
            doctor.setEmail(doctorDetails.getEmail());
        }

        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}
