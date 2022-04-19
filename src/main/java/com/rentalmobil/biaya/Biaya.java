package com.rentalmobil.biaya;

import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.penyewa.Penyewa;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "biaya")
@ToString
@AllArgsConstructor

public class Biaya implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_biaya")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam", nullable = false)
    private Date jam;

    @Column(name = "biaya")
    private Integer biaya;
}
