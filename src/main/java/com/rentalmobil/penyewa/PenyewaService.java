package com.rentalmobil.penyewa;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.biaya.BiayaRepository;
import com.rentalmobil.biaya.BiayaService;
import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.mobil.MobilRepository;
import com.rentalmobil.mobil.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PenyewaService implements IPenyewaService{
    @Autowired private PenyewaRepository repo;

    @Autowired private MobilRepository mobilRepository;

    @Autowired private MobilService mobilService;

    @Autowired private BiayaRepository biayaRepository;

    @Autowired private BiayaService biayaService;

    @Override
    public List<Penyewa> getAllPenyewa(){
        List<Penyewa> list = new ArrayList<>();
        repo.findAll().forEach(e ->list.add(e));
        return list;
    }

    @Override
    public List<Penyewa> listAll() {
        return  (List<Penyewa>) repo.findAll();
    }

    @Override
    public Penyewa addPenyewa(Penyewa penyewa){
        Biaya biaya = new Biaya();
        biaya.setJam(new Date());
        biaya.setId(penyewa.getId());
        penyewa.setBiaya(biaya);
        repo.save(penyewa);
        return penyewa;
    }


    public Penyewa get(Integer id) throws PenyewaNotFoundException {
        Optional<Penyewa> result = repo.findById(id);
        if (result .isPresent()) {
            return result.get();
        }
        throw new PenyewaNotFoundException("Tidak dapat menemukan penyewa dengan ID" + id);
    }

    public void delete(Integer id) throws PenyewaNotFoundException {
        Integer count = repo.countById(id);
        if (count == null || count == 0){
            throw new PenyewaNotFoundException("Tidak dapat menemukan penyewa dengan ID" + id);
        }
        repo.deleteById(id);
    }
}
