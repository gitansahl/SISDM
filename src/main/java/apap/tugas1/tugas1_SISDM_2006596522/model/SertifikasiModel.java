package apap.tugas1.tugas1_SISDM_2006596522.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sertifikasi")
public class SertifikasiModel implements Serializable {

    @Id
    @Column(name = "id_sertifikasi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSertifikasi;

    @NotNull
    @Size(max=255)
    @Column(name = "nama")
    private String nama;

    @OneToMany(mappedBy = "sertifikasi",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<SertifikasiKaryawan> listKaryawan;
}
