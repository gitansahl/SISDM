package apap.tugas1.tugas1_SISDM_2006596522.controller;

import apap.tugas1.tugas1_SISDM_2006596522.model.*;
import apap.tugas1.tugas1_SISDM_2006596522.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class KaryawanController {
    @Qualifier("karyawanServiceImpl")
    @Autowired
    KaryawanService karyawanService;

    @Qualifier("sertifikasiServiceImpl")
    @Autowired
    SertifikasiService sertifikasiService;

    @Qualifier("sertifikasiKaryawanServiceImpl")
    @Autowired
    SertifikasiKaryawanService sertifikasiKaryawanService;

    @Autowired
    PresensiService presensiService;

    @Autowired
    TugasService tugasService;


    @GetMapping("/karyawan")
    private String pageDaftarKaryawan(Model model) {
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();

        model.addAttribute("listKaryawan", listKaryawan);

        return "daftar-karyawan";
    }

    @GetMapping("/karyawan/tambah")
    private String pageFormTambahKaryawan(Model model) {
        KaryawanModel karyawan = new KaryawanModel();
        List<SertifikasiKaryawan> listSertifikasiNew = new ArrayList<>();

        karyawan.setListSertifikasiKaryawan(listSertifikasiNew);
        karyawan.getListSertifikasiKaryawan().add(new SertifikasiKaryawan());

        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);

        return "form-tambah-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"addRowSertifikasi"})
    public String addRowSertifikasiMultipleTambah(@ModelAttribute KaryawanModel karyawan,
                                    Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }
        karyawan.getListSertifikasiKaryawan().add(new SertifikasiKaryawan());

        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);

        return "form-tambah-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"deleteRowSertifikasi"})
    private String deleteRowSertifikasiMultipleTambah(
            @ModelAttribute KaryawanModel karyawan,
            @RequestParam("deleteRowSertifikasi") Integer row,
            Model model) {
        final Integer rowId = Integer.valueOf(row);
        karyawan.getListSertifikasiKaryawan().remove(rowId.intValue());

        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);

        return "form-tambah-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"save"})
    public String TambahKaryawanSubmitPage(@ModelAttribute KaryawanModel karyawan,
                                      Model model) {
        List<SertifikasiKaryawan> listSertifikasi = karyawan.getListSertifikasiKaryawan();
        karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        karyawanService.tambahKaryawan(karyawan);

        karyawan.setListSertifikasiKaryawan(listSertifikasi);
        if (karyawan.getListSertifikasiKaryawan() == null) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }

        for (SertifikasiKaryawan sertifikasiKaryawan: karyawan.getListSertifikasiKaryawan()) {
            sertifikasiKaryawan.setKaryawan(karyawan);
            sertifikasiKaryawan.setSertifikasi(sertifikasiService.getSertifikasiByIdSertifikasi(sertifikasiKaryawan.getSertifikasi().getIdSertifikasi()));
            System.out.println("A");
            sertifikasiKaryawan.setNoSetifikasi(sertifikasiKaryawanService.generateNoSertifikasi(sertifikasiKaryawan));
        }

        karyawanService.tambahKaryawan(karyawan);

        model.addAttribute("namaDepan", karyawan.getNamaDepan());
        model.addAttribute("namaBelakang", karyawan.getNamaBelakang());

        return "tambah-karyawan";
    }

    @GetMapping("/karyawan/{idKaryawan}")
    private String pageDetailKaryawan(@PathVariable("idKaryawan") Long idKaryawan,
                                      Model model) {
        KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);
        if (karyawan==null) {
            model.addAttribute("msg", "Karyawan dengan id " + idKaryawan + " tidak ditemukan");
            return "error";
        }
        model.addAttribute("karyawan", karyawan);
        return "detail-karyawan";
    }

    @GetMapping("/karyawan/{idKaryawan}/ubah")
    private String pageFormUbahKaryawan(@PathVariable("idKaryawan") Long idKaryawan,
                                      Model model) {
        KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);
        if (karyawan==null) {
            model.addAttribute("msg", "Karyawan dengan id " + idKaryawan + " tidak ditemukan");
            return "error";
        }
        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();
        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);
        return "form-ubah-karyawan";
    }

    @PostMapping(value = "/karyawan/{idKaryawan}/ubah", params = {"addRowSertifikasi"})
    public String addRowSertifikasiMultipleUbah(@ModelAttribute KaryawanModel karyawan,
                                                  @PathVariable("idKaryawan") Long idKaryawan,
                                                  Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }
        karyawan.getListSertifikasiKaryawan().add(new SertifikasiKaryawan());

        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);

        return "form-ubah-karyawan";
    }

    @PostMapping(value = "/karyawan/{idKaryawan}/ubah", params = {"deleteRowSertifikasi"})
    private String deleteRowSertifikasiMultipleUbah(@ModelAttribute KaryawanModel karyawan,
                                                      @PathVariable("idKaryawan") Long idKaryawan,
                                                      @RequestParam("deleteRowSertifikasi") Integer row,
                                                      Model model) {
        final Integer rowId = Integer.valueOf(row);

        sertifikasiKaryawanService.deleteSertifikasiKaryawan(karyawan.getListSertifikasiKaryawan().get(rowId.intValue()));
        karyawan.getListSertifikasiKaryawan().remove(rowId.intValue());

        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);

        return "form-ubah-karyawan";
    }

    @PostMapping(value = "/karyawan/{idKaryawan}/ubah", params = {"save"})
    public String UbahKaryawanSubmitPage(@ModelAttribute KaryawanModel karyawan,
                                        @PathVariable("idKaryawan") Long idKaryawan,
                                        Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }

        for (SertifikasiKaryawan sertifikasiKaryawan: karyawan.getListSertifikasiKaryawan()) {
            sertifikasiKaryawan.setKaryawan(karyawan);
            sertifikasiKaryawan.setSertifikasi(sertifikasiService.getSertifikasiByIdSertifikasi(sertifikasiKaryawan.getSertifikasi().getIdSertifikasi()));
            sertifikasiKaryawan.setNoSetifikasi(sertifikasiKaryawanService.generateNoSertifikasi(sertifikasiKaryawan));
        }

        sertifikasiKaryawanService.deleteSertifikasiKaryawanByIdKaryawan(karyawan.getIdKaryawan());

        karyawanService.tambahKaryawan(karyawan);

        model.addAttribute("namaDepan", karyawan.getNamaDepan());
        model.addAttribute("namaBelakang", karyawan.getNamaBelakang());

        return "ubah-karyawan";
    }

    @PostMapping(value = "/karyawan/{idKaryawan}/hapus")
    public String deleteKaryawanSubmitPage(@PathVariable("idKaryawan") Long idKaryawan,
                                           Model model) {
        KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);

        model.addAttribute("namaDepan", karyawan.getNamaDepan());
        model.addAttribute("namaBelakang", karyawan.getNamaBelakang());

        for (PresensiModel p : karyawan.getListPresensi()) {
            PresensiModel presensi = presensiService.getPresensiModelByIdPresensi(p.getIdPresensi());
            for (TugasModel t: presensi.getListTugas()) {
                TugasModel tugas = tugasService.getTugasByIdTugas(t.getIdTugas());
                tugas.setPresensi(null);
                tugasService.tambahTugas(tugas);
            }
        }

        karyawanService.deleteKaryawan(karyawan);

        return "hapus-karyawan";

    }

    @GetMapping("/filter-sertifikasi")
    private String filterKaryawan(Model model) {
        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();


        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);
        model.addAttribute("listKaryawan", listKaryawan);
        return "filter-karyawan";
    }

    @PostMapping("/filter-sertifikasi")
    private String filterKaryawanByIdSertifikasi(@RequestParam("id-sertifikasi") Long idSertitikasi,
                                                 Model model) {
        List<SertifikasiModel> listSertifikasiExisting = sertifikasiService.getListSertifikasi();

        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawanByIdSertifikasi(idSertitikasi);

        List<KaryawanModel> listKaryawan = new ArrayList<>();

        for (SertifikasiKaryawan sertifikasiKaryawan : listSertifikasiKaryawan) {
            Long idKaryawan = sertifikasiKaryawan.getKaryawan().getIdKaryawan();
            KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);
            listKaryawan.add(karyawan);
        }

        model.addAttribute("listSertifikasiExisting", listSertifikasiExisting);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("idSertifikasi", idSertitikasi);
        return "filter-karyawan";
    }

    @GetMapping("/karyawan/{idKaryawan}/insentif")
    private String insentifKaryawan(@PathVariable("idKaryawan") Long idKaryawan,
                                    Model model) {
        KaryawanModel karyawan = karyawanService.getKaryawanModelByIdKaryawan(idKaryawan);

        Map<String, Long> insentifMap = new HashMap<>();
        insentifMap.put("Sertifikasi", 0L);
        insentifMap.put("Presensi", 0L);
        insentifMap.put("Tugas", 0L);

        Long total = 0L;

        for (SertifikasiKaryawan sertifikasiKaryawan: karyawan.getListSertifikasiKaryawan()) {
            Long insentif = insentifMap.getOrDefault("Sertifikasi", 0L);
            insentifMap.put("Sertifikasi", insentif+3000L);
            total += 3000L;
        }

        for (PresensiModel presensiModel : karyawan.getListPresensi()) {
            PresensiModel presensi = presensiService.getPresensiModelByIdPresensi(presensiModel.getIdPresensi());

            if (presensi.getStatus().equals(0)) {
                Long insentif = insentifMap.getOrDefault("Presensi", 0L);
                insentifMap.put("Presensi", insentif-1000L);
                total -= 1000L;
            }

            for (TugasModel tugasModel : presensi.getListTugas()) {
                TugasModel tugas = tugasService.getTugasByIdTugas(tugasModel.getIdTugas());
                if (tugas.getStatus().equals(2)) {
                    Long insentif = insentifMap.getOrDefault("Tugas", 0L);
                    insentifMap.put("Tugas", insentif+ (1000L * tugas.getStoryPoint()));
                    total += (1000L * tugas.getStoryPoint());
                }
            }
        }

        if (total <= 0) {
            total = 0L;
        }

        karyawan.setInsentifKaryawan(total);
        karyawanService.tambahKaryawan(karyawan);

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("insentifMap", insentifMap);
        return "insentif-karyawan";
    }




}
