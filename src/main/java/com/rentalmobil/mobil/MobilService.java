package com.rentalmobil.mobil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MobilService implements IMobilService{
    @Autowired
    private MobilRepository mobilRepository;

    @Override
    public List<Mobil> getAllMobil(){
        List<Mobil> list = new ArrayList<>();
        mobilRepository.findAll().forEach(e ->list.add(e));
        return list;
    }

    @Override
    public List<Mobil> listAll() {
        return  (List<Mobil>) mobilRepository.findAll();
    }

    @Override
    public Mobil addMobil(Mobil mobil){
        mobilRepository.save(mobil);
        return mobil;
    }

    public Mobil get(Integer id) throws MobilNotFoundException {
        Optional<Mobil> result = mobilRepository.findById(id);
        if (result .isPresent()) {
            return result.get();
        }
        throw new MobilNotFoundException("Tidak dapat menemukan mobil dengan ID" + id);
    }

    public void delete(Integer id) throws MobilNotFoundException {
        Integer count = mobilRepository.countById(id);
        if (count == null || count == 0){
            throw new MobilNotFoundException("Tidak dapat menemukan mobil dengan ID" + id);
        }
        mobilRepository.deleteById(id);
    }
}
