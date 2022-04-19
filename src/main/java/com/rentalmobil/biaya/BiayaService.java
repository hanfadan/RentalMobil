package com.rentalmobil.biaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class BiayaService implements IBiayaService{
    @Autowired
    private BiayaRepository biayaRepository;

    @Override
    public List<Biaya> getAllBiaya(){
        List<Biaya> list = new ArrayList<>();
        biayaRepository.findAll().forEach(e ->list.add(e));
        return list;
    }

    @Override
    public List<Biaya> listAll() {
        return  (List<Biaya>) biayaRepository.findAll();
    }

    @Override
    public Biaya addBiaya(Biaya biaya){
        biayaRepository.save(biaya);
        return biaya;
    }

    public Biaya get(Integer id) throws BiayaNotFoundException {
        Optional<Biaya> result = biayaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new BiayaNotFoundException("Tidak dapat menemukan biaya dengan ID" + id);
    }

    public void delete(Integer id) throws BiayaNotFoundException {
        Integer count = biayaRepository.countById(id);
        if (count == null || count == 0) {
            throw new BiayaNotFoundException("Tidak dapat menemukan biaya dengan ID" + id);
        }
        biayaRepository.deleteById(id);
    }
}
