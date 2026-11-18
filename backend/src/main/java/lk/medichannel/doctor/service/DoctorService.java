
package lk.medichannel.doctor.service;

import lk.medichannel.doctor.entity.Doctor;
import lk.medichannel.doctor.repository.DoctorRepository;
import lk.medichannel.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with ID: " + id));
    }

    // Create doctor
    public Doctor createDoctor(Doctor doctor) {
        doctor.setActive(true);
        return doctorRepository.save(doctor);
    }

    // Update doctor
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {

        Doctor existingDoctor = getDoctorById(id);

        existingDoctor.setUserId(updatedDoctor.getUserId());
        existingDoctor.setSpecialisationId(
                updatedDoctor.getSpecialisationId());
        existingDoctor.setQualification(
                updatedDoctor.getQualification());
        existingDoctor.setExperienceYears(
                updatedDoctor.getExperienceYears());
        existingDoctor.setConsultationFee(
                updatedDoctor.getConsultationFee());

        return doctorRepository.save(existingDoctor);
    }

    // Get doctors by specialisation
    public List<Doctor> getDoctorsBySpecialisation(
            Long specialisationId) {

        return doctorRepository.findBySpecialisationId(
                specialisationId);
    }

    // Get active doctors
    public List<Doctor> getActiveDoctors() {
        return doctorRepository.findByActiveTrue();
    }

    // Deactivate doctor
    public Doctor deactivateDoctor(Long id) {

        Doctor doctor = getDoctorById(id);

        doctor.setActive(false);

        return doctorRepository.save(doctor);
    }

    // Search doctors by name
    public List<Doctor> searchDoctors(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                    "Search keyword cannot be empty");
        }

        return doctorRepository.searchDoctorsByName(
                keyword.trim());
    }

    // Delete doctor
    @Transactional
    public void deleteDoctor(Long id) {

        Doctor doctor = getDoctorById(id);

        doctorRepository.delete(doctor);

    }
}
