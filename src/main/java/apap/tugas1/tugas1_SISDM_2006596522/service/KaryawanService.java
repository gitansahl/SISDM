package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.KaryawanModel;

import java.util.List;

public interface KaryawanService {
    List<KaryawanModel> getListKaryawan();
    KaryawanModel getKaryawanModelByIdKaryawan(Long idKaryawan);
    void tambahKaryawan(KaryawanModel karyawan);
    void deleteKaryawan(KaryawanModel karyawan);
}
