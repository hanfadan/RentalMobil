package com.rentalmobil.penyewa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(nullable = false, name = "tanggal_mulai_sewa")
    private LocalDate tanggal_mulai_sewa;

    @Column(nullable = false, name = "tanggal_selesai_sewa")
    private LocalDate tanggal_selesai_sewa;

    @Column(nullable = false, name = "status")
    private Boolean status;

    @Column(nullable = false, name = "keterangan")
    private String keterangan;

    @Override
    public String toString() {
        return "Penyewa{" +
                "id=" + id +
                ", nama_penyewa='" + nama_penyewa + '\'' +
                ", tanggal_mulai_sewa=" + tanggal_mulai_sewa +
                ", tanggal_selesai_sewa=" + tanggal_selesai_sewa +
                ", status='" + status + '\'' +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}
