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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "setifikasi_karyawan")
public class SertifikasiKaryawan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sertifikasi_karyawan")
    private Long idSertifikasiKaryawan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sertifikasi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SertifikasiModel sertifikasi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KaryawanModel karyawan;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_pengembalian")
    private LocalDate tanggalPengambilan;

    @Size(max = 14)
    @Column(name = "no_sertifikasi")
    private String noSetifikasi;
}
