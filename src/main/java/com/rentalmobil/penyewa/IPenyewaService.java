package com.rentalmobil.penyewa;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.mobil.Mobil;

import java.util.List;

public interface IPenyewaService {
    List<Penyewa> getAllPenyewa();

    List<Penyewa> listAll();

    Penyewa addPenyewa(Penyewa penyewa);
}
