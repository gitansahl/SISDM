package apap.tugas1.tugas1_SISDM_2006596522.service;


import apap.tugas1.tugas1_SISDM_2006596522.model.SertifikasiModel;
import apap.tugas1.tugas1_SISDM_2006596522.repository.SertifikasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SertifikasiServiceImpl implements SertifikasiService{

    @Autowired
    SertifikasiDb sertifikasiDb;

    @Override
    public List<SertifikasiModel> getListSertifikasi() {
        return sertifikasiDb.findAll();
    }

    @Override
    public SertifikasiModel getSertifikasiByIdSertifikasi(Long idSertifikasi) {
        Optional<SertifikasiModel> sertifikasi = sertifikasiDb.getSertifikasiModelByIdSertifikasi(idSertifikasi);
        if (sertifikasi.isPresent()) {
            return sertifikasi.get();
        }
        return null;
    }
}
