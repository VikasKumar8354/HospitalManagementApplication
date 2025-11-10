package com.Hospital.HospitalManagement.Service;

import com.Hospital.HospitalManagement.Entity.Insurance;
import com.Hospital.HospitalManagement.Repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    // Create insurance
    public Insurance createInsurance(Insurance insurance) {
        if(insuranceRepository.existsByPolicyNumber(insurance.getPolicyNumber())) {
            throw new RuntimeException("Insurance with policy number '" + insurance.getPolicyNumber() + "' already exists.");
        }
        return insuranceRepository.save(insurance);
    }

    // Update insurance
    public Insurance updateInsurance(Long id, Insurance insuranceDetails) {
        Insurance insurance = getInsuranceById(id);

        if(insuranceDetails.getPolicyNumber() != null && !insuranceDetails.getPolicyNumber().equals(insurance.getPolicyNumber())) {
            if(insuranceRepository.existsByPolicyNumber(insuranceDetails.getPolicyNumber())) {
                throw new RuntimeException("Insurance with policy number '" + insuranceDetails.getPolicyNumber() + "' already exists.");
            }
            insurance.setPolicyNumber(insuranceDetails.getPolicyNumber());
        }

        if(insuranceDetails.getProvider() != null) {
            insurance.setProvider(insuranceDetails.getProvider());
        }

        if(insuranceDetails.getValidUnit() != null) {
            insurance.setValidUnit(insuranceDetails.getValidUnit());
        }

        return insuranceRepository.save(insurance);
    }

    // Get insurance by ID
    public Insurance getInsuranceById(Long id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found with id " + id));
    }

    // Get insurance by policy number
    public Insurance getInsuranceByPolicyNumber(String policyNumber) {
        return insuranceRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Insurance not found with policy number " + policyNumber));
    }

    // Get all insurances
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    // Delete insurance
    public void deleteInsurance(Long id) {
        Insurance insurance = getInsuranceById(id);
        insuranceRepository.delete(insurance);
    }
}
