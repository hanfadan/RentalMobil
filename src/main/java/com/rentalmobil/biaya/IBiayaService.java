package com.rentalmobil.biaya;

import java.util.List;

public interface IBiayaService {
    List<Biaya> getAllBiaya();

    List<Biaya> listAll();

    Biaya addBiaya(Biaya biaya);
}
