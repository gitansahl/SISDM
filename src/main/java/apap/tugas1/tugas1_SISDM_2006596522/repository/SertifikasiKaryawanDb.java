package apap.tugas1.tugas1_SISDM_2006596522.repository;

import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiKaryawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SertifikasiKaryawanDb extends JpaRepository<SertifikasiKaryawan, Long> {
    Optional<SertifikasiKaryawan> getSertifikasiKaryawanByNoSetifikasi(String NoSertifikasi);
    List<SertifikasiKaryawan> findAllBySertifikasi_IdSertifikasi(Long idSertifikasi);

    void deleteSertifikasiKaryawansByKaryawan_IdKaryawan(Long idKaryawan);
}
