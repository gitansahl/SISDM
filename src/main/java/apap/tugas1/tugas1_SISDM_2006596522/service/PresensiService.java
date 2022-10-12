package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.PresensiModel;

import java.util.List;

public interface PresensiService {
    List<PresensiModel> getListPresensi();
    void tambahPresensi(PresensiModel presensi);

    PresensiModel getPresensiModelByIdPresensi(Long idPresensi);
}
