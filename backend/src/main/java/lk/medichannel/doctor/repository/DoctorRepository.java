
package lk.medichannel.doctor.repository;

import lk.medichannel.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialisationId(Long specialisationId);

    List<Doctor> findByActiveTrue();

    List<Doctor> findByActive(boolean active);

    // Search doctors by name
    @Query(value = """
            SELECT d.*
            FROM doctors d
            INNER JOIN users u ON d.user_id = u.id
            WHERE LOWER(u.full_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """, nativeQuery = true)
    List<Doctor> searchDoctorsByName(@Param("keyword") String keyword);

}

