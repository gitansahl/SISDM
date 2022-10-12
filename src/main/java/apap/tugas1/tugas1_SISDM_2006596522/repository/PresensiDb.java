package apap.tugas1.tugas1_SISDM_2006596522.repository;

import apap.tugas1.tugas1_SISDM_2006596522.model.PresensiModel;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresensiDb extends JpaRepository<PresensiModel, Long> {
    Optional<PresensiModel> findPresensiModelByIdPresensi(Long idPresensi);
}
