package com.rentalmobil.biaya;

import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.mobil.MobilNotFoundException;
import com.rentalmobil.mobil.MobilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BiayaService {
    @Autowired
    private BiayaRepository biayaRepository;

    public List<Biaya> listAll() {
        return (List<Biaya>) biayaRepository.findAll();
    }

    public Biaya saveBiaya(Biaya biaya) {
        biayaRepository.save(biaya);
        return biaya;
    }

    public Biaya get(Integer id_penyewa) throws BiayaNotFoundException {
        Optional<Biaya> result = biayaRepository.findById(id_penyewa);
        if (result.isPresent()) {
            return result.get();
        }
        throw new BiayaNotFoundException("Tidak dapat menemukan biaya dengan ID" + id_penyewa);
    }

    public void delete(Integer penyewa) throws BiayaNotFoundException {
        Integer count = biayaRepository.countById(penyewa);
        if (count == null || count == 0) {
            throw new BiayaNotFoundException("Tidak dapat menemukan biaya dengan ID" + penyewa);
        }
        biayaRepository.deleteById(penyewa);
    }
}
