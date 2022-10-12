package apap.tugas1.tugas1_SISDM_2006596522.repository;

import apap.tugas1.tugas1_SISDM_2006596522.model.TugasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TugasDb extends JpaRepository<TugasModel, Long> {
    List<TugasModel> findAllByPresensiIsNull();
    List<TugasModel> findAllByPresensiIsNullOrPresensi_IdPresensi(Long idPresensi);
    List<TugasModel> findAllByPresensi_IdPresensi(Long idPresensi);

    Optional<TugasModel> getTugasModelByIdTugas(Long idTugas);

    List<TugasModel> findAllByPresensi_Karyawan_IdKaryawanAndAndStatus(Long idKaryawan, Integer status);
}
