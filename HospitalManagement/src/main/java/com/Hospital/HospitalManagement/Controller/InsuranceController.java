package com.Hospital.HospitalManagement.Controller;

import com.Hospital.HospitalManagement.Entity.Insurance;
import com.Hospital.HospitalManagement.Service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    @Autowired
    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping
    public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) {
        Insurance createdInsurance = insuranceService.createInsurance(insurance);
        return ResponseEntity.ok(createdInsurance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable Long id,
                                                     @RequestBody Insurance insurance) {
        Insurance updatedInsurance = insuranceService.updateInsurance(id, insurance);
        return ResponseEntity.ok(updatedInsurance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Long id) {
        Insurance insurance = insuranceService.getInsuranceById(id);
        return ResponseEntity.ok(insurance);
    }

    @GetMapping("/policy/{policyNumber}")
    public ResponseEntity<Insurance> getInsuranceByPolicyNumber(@PathVariable String policyNumber) {
        Insurance insurance = insuranceService.getInsuranceByPolicyNumber(policyNumber);
        return ResponseEntity.ok(insurance);
    }

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        return ResponseEntity.ok(insurances);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.ok("Insurance deleted successfully");
    }
}
