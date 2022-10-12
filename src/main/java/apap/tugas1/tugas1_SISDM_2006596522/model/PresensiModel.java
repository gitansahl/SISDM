package apap.tugas1.tugas1_SISDM_2006596522.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "presensi")
public class PresensiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presensi")
    private Long idPresensi;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @NotNull
    @Column(name = "tanggal")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    @NotNull
    @Column(name = "waktu_masuk")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuMasuk;

    @NotNull
    @Column(name = "waktu_keluar")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuKeluar;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KaryawanModel karyawan;

    @OneToMany(mappedBy = "presensi", fetch = FetchType.LAZY)
    private List<TugasModel> listTugas;
}
