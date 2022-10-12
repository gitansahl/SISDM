package apap.tugas1.tugas1_SISDM_2006596522.controller;

import apap.tugas1.tugas1_SISDM_2006596522.model.KaryawanModel;
import apap.tugas1.tugas1_SISDM_2006596522.model.TugasModel;
import apap.tugas1.tugas1_SISDM_2006596522.service.KaryawanService;
import apap.tugas1.tugas1_SISDM_2006596522.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TugasController {

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    TugasService tugasService;

    @GetMapping("/filter-tugas")
    private String filterTugas(Model model) {
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        List<TugasModel> listTugas = tugasService.getListTugas();

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugas", listTugas);
        return "filter-tugas";
    }

    @PostMapping("/filter-tugas")
    private String filterTugasByIdKaryawanAndStatus(@RequestParam("id-karyawan") Long idKaryawan,
                                                    @RequestParam("status") Integer status,
                                                    Model model) {
        List<KaryawanModel> listKaryawanExisting = karyawanService.getListKaryawan();

        List<TugasModel> listTugas = tugasService.getListTugasByIdKaryawanAndStatus(idKaryawan, status);

        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        model.addAttribute("listTugas", listTugas);
        return "filter-tugas";
    }

    @GetMapping("/tambah-tugas")
    private String pageFormTambahTugas(Model model) {
        TugasModel tugas = new TugasModel();

        model.addAttribute("tugas", tugas);
        return "form-tambah-tugas";
    }

    @PostMapping("/tambah-tugas")
    private String tambahTugasSubmitPage(@ModelAttribute TugasModel tugas,
                                         Model model) {
        tugasService.tambahTugas(tugas);

        model.addAttribute("tugas", tugas);
        return "tambah-tugas";
    }


}
