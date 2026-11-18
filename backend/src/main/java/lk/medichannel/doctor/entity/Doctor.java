
package lk.medichannel.doctor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @NotNull(message = "Specialisation ID is required")
    @Positive(message = "Specialisation ID must be positive")
    @Column(name = "specialisation_id", nullable = false)
    private Long specialisationId;

    @Size(max = 200, message = "Qualification cannot exceed 200 characters")
    @Column(name = "qualification", length = 200)
    private String qualification;

    @NotNull(message = "Experience years is required")
    @Min(value = 0, message = "Experience years cannot be negative")
    @Column(name = "experience_years")
    private Integer experienceYears;

    @NotNull(message = "Consultation fee is required")
    @DecimalMin(value = "0.00", message = "Consultation fee cannot be negative")
    @Column(name = "consultation_fee", nullable = false)
    private BigDecimal consultationFee;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpecialisationId() {
        return specialisationId;
    }

    public void setSpecialisationId(Long specialisationId) {
        this.specialisationId = specialisationId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(BigDecimal consultationFee) {
        this.consultationFee = consultationFee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

