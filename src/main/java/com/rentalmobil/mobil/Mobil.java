package com.rentalmobil.mobil;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.penyewa.Penyewa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mobil")

public class Mobil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_mobil",nullable = false)
    private String nama_mobil;

    @Column(name = "no_polisi",nullable = false)
    private String no_polisi;

    @Column(name = "harga_sewa",nullable = false)
    private String harga_sewa;

    @ManyToOne
    private Penyewa penyewa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_biaya")
    private Biaya biaya;

    @Override
    public String toString() {
        return "Mobil{" +
                "id=" + id +
                ", nama_mobil='" + nama_mobil + '\'' +
                ", no_polisi='" + no_polisi + '\'' +
                ", harga_sewa='" + harga_sewa + '\'' +
                ", penyewa=" + penyewa +
                ", biaya=" + biaya +
                '}';
    }
}
