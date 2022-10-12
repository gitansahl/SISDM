package apap.tugas1.tugas1_SISDM_2006596522.controller;

import apap.tugas1.tugas1_SISDM_2006596522.model.*;
import apap.tugas1.tugas1_SISDM_2006596522.service.KaryawanService;
import apap.tugas1.tugas1_SISDM_2006596522.service.PresensiService;
import apap.tugas1.tugas1_SISDM_2006596522.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PresensiController {
    @Autowired
    PresensiService presensiService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    TugasService tugasService;

    @GetMapping("/presensi")
    private String pageDaftarPresensi(Model model) {
        List<PresensiModel> listPresensi = presensiService.getListPresensi();

        model.addAttribute("listPresensi", listPresensi);

        return "daftar-presensi";
    }

    @GetMapping("/presensi/tambah")
    private String pageFromTambahPresensi(Model model) {
        List<TugasModel> listTugasExisting = tugasService.getListTugasNoPresensi();
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        PresensiModel presensi = new PresensiModel();
        presensi.setListTugas(new ArrayList<>());

        if (listTugasExisting.size() > 0) {
            presensi.getListTugas().add(new TugasModel());
        }

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugasExisting", listTugasExisting);
        model.addAttribute("presensi", presensi);

        return "form-tambah-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"addRowTugas"})
    private String addRowTugasMultipleTambah(@ModelAttribute PresensiModel presensi,
                                             Model model) {
        List<TugasModel> listTugasExisting = tugasService.getListTugasNoPresensi();
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        if (presensi.getListTugas() == null) {
            presensi.setListTugas(new ArrayList<>());
        }
        presensi.getListTugas().add(new TugasModel());

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugasExisting", listTugasExisting);
        model.addAttribute("presensi", presensi);

        return "form-tambah-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"deleteRowTugas"})
    private String deleteRowSertifikasiMultipleTambah(
            @ModelAttribute PresensiModel presensi,
            @RequestParam("deleteRowTugas") Integer row,
            Model model) {
        final Integer rowId = Integer.valueOf(row);
        presensi.getListTugas().remove(rowId.intValue());

        List<TugasModel> listTugasExisting = tugasService.getListTugasNoPresensi();
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugasExisting", listTugasExisting);
        model.addAttribute("presensi", presensi);

        return "form-tambah-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"save"})
    private String TambahPresensiSubmitPage(@ModelAttribute PresensiModel presensi,
                                           Model model) {
        Long idKaryawan = presensi.getKaryawan().getIdKaryawan();
        KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);
        presensi.setKaryawan(karyawan);

        LocalTime tujuh = LocalTime.of(7,0);
        if (presensi.getWaktuMasuk().isAfter(tujuh)) {
            presensi.setStatus(0);
        } else {
            presensi.setStatus(1);
        }

        presensiService.tambahPresensi(presensi);

        if (presensi.getListTugas() == null) {
            presensi.setListTugas(new ArrayList<>());
        }

        for (TugasModel tugas : presensi.getListTugas()) {
            TugasModel tugasExisting = tugasService.getTugasByIdTugas(tugas.getIdTugas());
            tugasExisting.setPresensi(presensi);
            tugasExisting.setStatus(tugas.getStatus());
            tugasService.tambahTugas(tugasExisting);
        }

        presensi.setListTugas(new ArrayList<>());

        presensiService.tambahPresensi(presensi);

        model.addAttribute("presensi", presensi);

        return "tambah-presensi";
    }

    @GetMapping("/presensi/{idPresensi}")
    private String pageDetailPresensi(@PathVariable("idPresensi") Long idPresensi,
                                      Model model) {
        PresensiModel presensi = presensiService.getPresensiModelByIdPresensi(idPresensi);

        model.addAttribute("presensi", presensi);

        return "detail-presensi";
    }

    @GetMapping("/presensi/{idPresensi}/ubah")
    private String pageFromUbahPresensi(@PathVariable("idPresensi") Long idPresensi,
                                        Model model) {
        List<TugasModel> listTugasExisting = tugasService.getListTugasByPresensiIsNullOrPresensi_idPresensi(idPresensi);
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        PresensiModel presensi = presensiService.getPresensiModelByIdPresensi(idPresensi);

        if (presensi==null) {
            model.addAttribute("msg", "Presensi dengan id " + idPresensi + " tidak ditemukan");
            return "error";
        }

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugasExisting", listTugasExisting);
        model.addAttribute("presensi", presensi);

        return "form-ubah-presensi";
    }

    @PostMapping(value = "/presensi/{idPresensi}/ubah", params = {"addRowTugas"})
    private String addRowTugasMultipleUbah(@ModelAttribute PresensiModel presensi,
                                           @PathVariable("idPresensi") Long idPresensi,
                                           Model model) {
        List<TugasModel> listTugasExisting = tugasService.getListTugasByPresensiIsNullOrPresensi_idPresensi(idPresensi);
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        if (presensi.getListTugas() == null) {
            presensi.setListTugas(new ArrayList<>());
        }
        presensi.getListTugas().add(new TugasModel());

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugasExisting", listTugasExisting);
        model.addAttribute("presensi", presensi);

        return "form-ubah-presensi";
    }

    @PostMapping(value = "/presensi/{idPresensi}/ubah", params = {"deleteRowTugas"})
    private String deleteRowSertifikasiMultipleUbah(
            @ModelAttribute PresensiModel presensi,
            @PathVariable("idPresensi") Long idPresensi,
            @RequestParam("deleteRowTugas") Integer row,
            Model model) {
        final Integer rowId = Integer.valueOf(row);

        presensi.getListTugas().remove(rowId.intValue());

        List<TugasModel> listTugasExisting = tugasService.getListTugasByPresensiIsNullOrPresensi_idPresensi(idPresensi);
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugasExisting", listTugasExisting);
        model.addAttribute("presensi", presensi);

        return "form-ubah-presensi";
    }

    @PostMapping(value = "/presensi/{idPresensi}/ubah", params = {"save"})
    private String UbahPresensiSubmitPage(@ModelAttribute PresensiModel presensi,
                                          @PathVariable("idPresensi") Long idPresensi,
                                          Model model) {
        Long idKaryawan = presensi.getKaryawan().getIdKaryawan();
        KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);
        presensi.setKaryawan(karyawan);

        LocalTime tujuh = LocalTime.of(7,0);
        if (presensi.getWaktuMasuk().isAfter(tujuh)) {
            presensi.setStatus(0);
        } else {
            presensi.setStatus(1);
        }

        if (presensi.getListTugas() == null) {
            presensi.setListTugas(new ArrayList<>());
        }

        List<TugasModel> tugasBefore = tugasService.getListTugasByPresensi_idPresensi(idPresensi);
        for (TugasModel tugas : tugasBefore) {
            tugas.setPresensi(null);
            tugasService.tambahTugas(tugas);
        }

        for (TugasModel tugas : presensi.getListTugas()) {
            TugasModel tugasExisting = tugasService.getTugasByIdTugas(tugas.getIdTugas());
            tugasExisting.setPresensi(presensi);
            tugasExisting.setStatus(tugas.getStatus());
            tugasService.tambahTugas(tugasExisting);
        }

        presensi.setListTugas(new ArrayList<>());

        presensiService.tambahPresensi(presensi);

        model.addAttribute("presensi", presensi);

        return "ubah-presensi";
    }
}
