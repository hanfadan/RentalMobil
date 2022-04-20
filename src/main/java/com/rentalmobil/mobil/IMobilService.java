package com.rentalmobil.mobil;

import java.util.List;

public interface IMobilService {
    List<Mobil> getAllMobil();

    List<Mobil> listAll();

    Mobil addMobil(Mobil mobil);
}
