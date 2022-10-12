package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.KaryawanModel;
import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiModel;

import java.util.List;

public interface SertifikasiService {
    List<SertifikasiModel> getListSertifikasi();
    SertifikasiModel getSertifikasiByIdSertifikasi(Long idSertifikasi);

}
