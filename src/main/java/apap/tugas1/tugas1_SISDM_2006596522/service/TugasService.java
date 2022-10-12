package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.TugasModel;

import java.util.List;
import java.util.Optional;

public interface TugasService {
    List<TugasModel> getListTugas();
    List<TugasModel> getListTugasNoPresensi();
    TugasModel getTugasByIdTugas(Long idTugas);
    void tambahTugas(TugasModel tugas);
    List<TugasModel> getListTugasByPresensiIsNullOrPresensi_idPresensi(Long idPresensi);
    List<TugasModel> getListTugasByPresensi_idPresensi(Long idPresensi);

    List<TugasModel> getListTugasByIdKaryawanAndStatus(Long idKaryawan, Integer status);
}
