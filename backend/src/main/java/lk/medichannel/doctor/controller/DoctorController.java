
package lk.medichannel.doctor.controller;

import lk.medichannel.doctor.entity.Doctor;
import lk.medichannel.doctor.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Get all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(
                doctorService.getAllDoctors());
    }

    // Search doctors by name
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchDoctors(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                doctorService.searchDoctors(keyword));
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.getDoctorById(id));
    }

    // Create doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(
            @Valid @RequestBody Doctor doctor) {

        Doctor createdDoctor =
                doctorService.createDoctor(doctor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDoctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody Doctor doctor) {

        return ResponseEntity.ok(
                doctorService.updateDoctor(id, doctor));
    }

    // Get doctors by specialisation
    @GetMapping("/specialisation/{specialisationId}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialisation(
            @PathVariable Long specialisationId) {

        return ResponseEntity.ok(
                doctorService.getDoctorsBySpecialisation(
                        specialisationId));
    }

    // Get active doctors
    @GetMapping("/active")
    public ResponseEntity<List<Doctor>> getActiveDoctors() {
        return ResponseEntity.ok(
                doctorService.getActiveDoctors());
    }

    // Deactivate doctor
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Doctor> deactivateDoctor(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.deactivateDoctor(id));
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }
}
