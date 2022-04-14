package com.rentalmobil.penyewa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenyewaService {
    @Autowired private PenyewaRepository repo;

    public List<Penyewa> listAll() {
        return  (List<Penyewa>) repo.findAll();
    }
}
