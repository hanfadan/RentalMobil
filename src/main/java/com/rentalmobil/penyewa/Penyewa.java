package com.rentalmobil.penyewa;
import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.mobil.Mobil;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "penyewa")
@ToString
@AllArgsConstructor

public class Penyewa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_penyewa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "nama_penyewa")
    private String nama_penyewa;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false, name = "tanggal_mulai_sewa")
    private Date tanggal_mulai_sewa;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false, name = "tanggal_selesai_sewa")
    private Date tanggal_selesai_sewa;

    @Column(nullable = false, name = "status")
    private Boolean status;

    @Column(nullable = false, name = "keterangan")
    private String keterangan;

    @ManyToOne
    @JoinColumn(name = "mobil_id")
    private Mobil mobil;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_biaya")
    private Biaya biaya;

}
