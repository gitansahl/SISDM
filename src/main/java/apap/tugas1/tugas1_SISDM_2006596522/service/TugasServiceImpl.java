package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.TugasModel;
import apap.tugas1.tugas1_SISDM_2006596522.repository.TugasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TugasServiceImpl implements TugasService{
    @Autowired
    TugasDb tugasDb;

    @Override
    public List<TugasModel> getListTugas() {
        return tugasDb.findAll();
    }

    @Override
    public List<TugasModel> getListTugasNoPresensi() {
        return tugasDb.findAllByPresensiIsNull();
    }

    @Override
    public TugasModel getTugasByIdTugas(Long idTugas) {
        Optional<TugasModel> tugas = tugasDb.findById(idTugas);
        if (tugas.isPresent()) {
            return tugas.get();
        }
        return null;
    }

    @Override
    public void tambahTugas(TugasModel tugasModel) {
        tugasDb.save(tugasModel);
    }

    @Override
    public List<TugasModel> getListTugasByPresensiIsNullOrPresensi_idPresensi(Long idPresensi) {
        return tugasDb.findAllByPresensiIsNullOrPresensi_IdPresensi(idPresensi);
    }

    @Override
    public List<TugasModel> getListTugasByPresensi_idPresensi(Long idPresensi) {
        return tugasDb.findAllByPresensi_IdPresensi(idPresensi);
    }

    @Override
    public List<TugasModel> getListTugasByIdKaryawanAndStatus(Long idKaryawan, Integer status) {
        return tugasDb.findAllByPresensi_Karyawan_IdKaryawanAndAndStatus(idKaryawan, status);
    }
}
