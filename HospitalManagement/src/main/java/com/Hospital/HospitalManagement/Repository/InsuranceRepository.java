package com.Hospital.HospitalManagement.Repository;

import com.Hospital.HospitalManagement.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByPolicyNumber(String policyNumber);
    boolean existsByPolicyNumber(String policyNumber);
}