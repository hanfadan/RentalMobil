package com.rentalmobil.mobil;
import com.rentalmobil.penyewa.Penyewa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobilService {
    @Autowired
    private MobilRepository mobilRepository;

    public List<Mobil> listAll() {
        return  (List<Mobil>) mobilRepository.findAll();
    }

    public Mobil saveMobil(Mobil mobil) {
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
        Long count = mobilRepository.countById(id);
        if (count == null || count == 0){
            throw new MobilNotFoundException("Tidak dapat menemukan mobil dengan ID" + id);
        }
        mobilRepository.deleteById(id);
    }
}
