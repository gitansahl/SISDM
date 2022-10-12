package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.KaryawanModel;
import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiKaryawan;
import apap.tugas1.tugas1_SISDM_2006596522.repository.SertifikasiKaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SertifikasiKaryawanServiceImpl implements SertifikasiKaryawanService {
    @Autowired
    SertifikasiKaryawanDb sertifikasiKaryawanDb;
    @Override
    public String generateNoSertifikasi(SertifikasiKaryawan sertifikasiKaryawan) {
        String noSertifikasi = "SER";

        int tahunLahir = sertifikasiKaryawan.getKaryawan().getTanggalLahir().getYear();
        int tanggalPengambilan = sertifikasiKaryawan.getTanggalPengambilan().getDayOfMonth();
        int bulanPengambilan = sertifikasiKaryawan.getTanggalPengambilan().getMonthValue();

        noSertifikasi += String.valueOf(
                    tahunLahir + Integer.parseInt(
                            String.valueOf(tanggalPengambilan) + String.valueOf(bulanPengambilan)));

        char hAwalSertif = sertifikasiKaryawan.getSertifikasi().getNama().toLowerCase().toCharArray()[0];
        if (((int) hAwalSertif - 60) < 10) {
            noSertifikasi += "0";
        }
        noSertifikasi += String.valueOf((int) hAwalSertif - 60);

        char hAwalKaryawan = sertifikasiKaryawan.getKaryawan().getNamaDepan().toLowerCase().toCharArray()[0];
        if (((int) hAwalKaryawan - 60) < 10) {
            noSertifikasi += "0";
        }
        noSertifikasi += String.valueOf((int) hAwalKaryawan - 60);

        long idKaryawan = sertifikasiKaryawan.getKaryawan().getIdKaryawan();
        while (idKaryawan < 100) {
            noSertifikasi += "0";
            idKaryawan *= 10;
        }
        noSertifikasi += String.valueOf(sertifikasiKaryawan.getKaryawan().getIdKaryawan());

        return noSertifikasi;
    }

    @Override
    public void deleteSertifikasiKaryawan(SertifikasiKaryawan sertifikasiKaryawan) {
        if (sertifikasiKaryawan.getNoSetifikasi() == null || sertifikasiKaryawan.getNoSetifikasi().equals("")) return;
        SertifikasiKaryawan sertifikasiKaryawan1 = getSertifikasiKaryawanByNoSertifikasi(sertifikasiKaryawan.getNoSetifikasi());
        if (sertifikasiKaryawan1 != null) {
            sertifikasiKaryawanDb.delete(sertifikasiKaryawan1);
        }
    }

    @Override
    public SertifikasiKaryawan getSertifikasiKaryawanByNoSertifikasi(String noSertifikasi) {
        Optional<SertifikasiKaryawan> sertifikasiKaryawan = sertifikasiKaryawanDb.getSertifikasiKaryawanByNoSetifikasi(noSertifikasi);
        if (sertifikasiKaryawan.isPresent()) {
            return sertifikasiKaryawan.get();
        }
        return null;
    }

    @Override
    public void deleteSertifikasiKaryawanByIdKaryawan(Long idKaryawan) {
        sertifikasiKaryawanDb.deleteSertifikasiKaryawansByKaryawan_IdKaryawan(idKaryawan);
    }

    @Override
    public List<SertifikasiKaryawan> getListSertifikasiKaryawanByIdSertifikasi(Long idSsertifikasi) {
        return sertifikasiKaryawanDb.findAllBySertifikasi_IdSertifikasi(idSsertifikasi);
    }
}
