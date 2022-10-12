package apap.tugas1.tugas1_SISDM_2006596522.repository;

import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SertifikasiDb extends JpaRepository<SertifikasiModel, Long> {
    Optional<SertifikasiModel> getSertifikasiModelByIdSertifikasi(Long idSertifikasi);
}
