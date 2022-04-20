package com.rentalmobil.penyewa;

import java.util.List;

public interface IPenyewaService {
    List<Penyewa> getAllPenyewa();

    List<Penyewa> listAll();

    Penyewa addPenyewa(Penyewa penyewa);
}
