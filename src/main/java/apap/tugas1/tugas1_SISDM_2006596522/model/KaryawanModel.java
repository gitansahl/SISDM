package apap.tugas1.tugas1_SISDM_2006596522.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "karyawan")
public class KaryawanModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_karyawan")
    private Long idKaryawan;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_depan")
    private String namaDepan;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_belakang")
    private String namaBelakang;

    @NotNull
    @Column(name = "jenis_kelamin")
    private Integer jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Column(name = "insentif_karyawan")
    private Long insentifKaryawan;

    @OneToMany(mappedBy = "karyawan",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List <PresensiModel> listPresensi;

    @OneToMany(mappedBy = "karyawan",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<SertifikasiKaryawan> listSertifikasiKaryawan;
}
