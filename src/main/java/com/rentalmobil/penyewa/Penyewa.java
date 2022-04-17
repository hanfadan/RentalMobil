package com.rentalmobil.penyewa;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.mobil.Mobil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "penyewa")
public class Penyewa {
    @Id
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
    @JoinColumn(name = "id_mobil")
    private Mobil mobil;

    @Override
    public String toString() {
        return "Penyewa{" +
                "id=" + id +
                ", nama_penyewa='" + nama_penyewa + '\'' +
                ", tanggal_mulai_sewa=" + tanggal_mulai_sewa +
                ", tanggal_selesai_sewa=" + tanggal_selesai_sewa +
                ", status=" + status +
                ", keterangan='" + keterangan + '\'' +
                ", mobil=" + mobil +
                '}';
    }
}
