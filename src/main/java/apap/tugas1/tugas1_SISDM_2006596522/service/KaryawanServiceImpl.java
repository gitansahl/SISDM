package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.KaryawanModel;
import apap.tugas1.tugas1_SISDM_2006596522.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<KaryawanModel> getListKaryawan() {
        return karyawanDb.findAll();
    }

    @Override
    public KaryawanModel getKaryawanModelByIdKaryawan(Long idKaryawan) {
        Optional<KaryawanModel> karyawan = karyawanDb.findKaryawanModelByIdKaryawan(idKaryawan);
        if (karyawan.isPresent()) {
            return karyawan.get();
        }
        return null;
    }

    @Override
    public void tambahKaryawan(KaryawanModel karyawan) {
        System.out.println("S");
        karyawanDb.save(karyawan);
    }

    @Override
    public void deleteKaryawan(KaryawanModel karyawan) {
        karyawanDb.delete(karyawan);
    }
}
