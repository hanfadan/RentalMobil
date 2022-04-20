package com.rentalmobil.mobil;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mobil")
@ToString
@AllArgsConstructor

public class Mobil implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_mobil")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_mobil",nullable = false)
    private String nama_mobil;

    @Column(name = "no_polisi",nullable = false)
    private String no_polisi;

    @Column(name = "harga_sewa",nullable = false)
    private String harga_sewa;

}


