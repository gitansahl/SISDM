package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.KaryawanModel;
import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiKaryawan;
import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiModel;

import java.util.List;

public interface SertifikasiKaryawanService {
    String generateNoSertifikasi(SertifikasiKaryawan sertifikasiKaryawan);
    void deleteSertifikasiKaryawan(SertifikasiKaryawan sertifikasiKaryawan);
    SertifikasiKaryawan getSertifikasiKaryawanByNoSertifikasi(String noSertifikasi);
    void deleteSertifikasiKaryawanByIdKaryawan(Long idKaryawan);

    List<SertifikasiKaryawan> getListSertifikasiKaryawanByIdSertifikasi(Long idSsertifikasi);
}
