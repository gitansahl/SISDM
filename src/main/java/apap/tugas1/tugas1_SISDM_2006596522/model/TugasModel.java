package apap.tugas1.tugas1_SISDM_2006596522.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tugas")
public class TugasModel implements Serializable {
    @Id
    @Column(name = "id_tugas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTugas;

    @NotNull
    @Size(max = 255)
    @Column(name = "name")
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi")
    private String deskripsi;

    @NotNull
    @Column(name = "story_point")
    private Integer storyPoint;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_presensi", referencedColumnName = "id_presensi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PresensiModel presensi;
}
