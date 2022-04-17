package com.rentalmobil.penyewa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenyewaService {
    @Autowired private PenyewaRepository repo;

    public List<Penyewa> listAll() {
        return  (List<Penyewa>) repo.findAll();
    }

    public Penyewa savePenyewa(Penyewa penyewa) {
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
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new PenyewaNotFoundException("Tidak dapat menemukan penyewa dengan ID" + id);
        }
        repo.deleteById(id);
    }
}
