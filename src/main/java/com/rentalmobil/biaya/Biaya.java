package com.rentalmobil.biaya;

import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.penyewa.Penyewa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "biaya")

public class Biaya {

    @Id
    @Column(name = "id_biaya")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIME)
    @Column(name = "jam", nullable = false)
    private Date jam;
    private Integer biaya;

    @OneToOne(mappedBy = "biaya")
    private Mobil id_mobil;

    @Override
    public String toString() {
        return "Biaya{" +
                "id=" + id +
                ", jam=" + jam +
                ", biaya=" + biaya +
                ", mobil=" + id_mobil +
                '}';
    }
}
