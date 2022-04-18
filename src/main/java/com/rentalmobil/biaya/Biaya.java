package com.rentalmobil.biaya;

import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.penyewa.Penyewa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "biaya")

public class Biaya {

    @Id
    @Column(name = "id_biaya")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIME)
    @Column(name = "jam", nullable = false)
    private Date jam;
    @Column(name = "biaya")
    private Integer biaya;

    @OneToMany(mappedBy = "biaya")
    private List<Mobil> mobil = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Penyewa penyewa;

    @Override
    public String toString() {
        return "Biaya{" +
                "id=" + id +
                ", jam=" + jam +
                ", biaya=" + biaya +
                ", mobil=" + mobil +
                ", penyewa=" + penyewa +
                '}';
    }

    public Biaya(Date jam, Integer biaya) {
        this.jam = jam;
        this.biaya = biaya;
    }
}
