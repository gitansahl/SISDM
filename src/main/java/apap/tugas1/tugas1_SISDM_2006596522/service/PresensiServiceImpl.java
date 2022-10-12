package apap.tugas1.tugas1_SISDM_2006596522.service;

import apap.tugas1.tugas1_SISDM_2006596522.model.PresensiModel;
import apap.tugas1.tugas1_SISDM_2006596522.repository.PresensiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresensiServiceImpl implements PresensiService{
    @Autowired
    PresensiDb presensiDb;

    @Override
    public List<PresensiModel> getListPresensi() {
        return presensiDb.findAll();
    }

    @Override
    public void tambahPresensi(PresensiModel presensi) {
        presensiDb.save(presensi);
    }

    @Override
    public PresensiModel getPresensiModelByIdPresensi(Long idPresensi) {
        Optional<PresensiModel> presensi = presensiDb.findPresensiModelByIdPresensi(idPresensi);
        if (presensi.isPresent()) {
            return presensi.get();
        }
        return null;
    }
}
