package com.rentalmobil.mobil;

import com.rentalmobil.penyewa.Penyewa;
import com.rentalmobil.penyewa.PenyewaNotFoundException;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mobil")

public class MobilRESTController {
    @Autowired
    private MobilService service;

    @GetMapping
    public List<Mobil> getAllMobil() { return service.listAll();}

    @PostMapping
    public Mobil CreateMobil(@RequestBody Mobil mobil) {
        return service.addMobil(mobil);
    }

    @PutMapping("{id}")
    public ResponseEntity<Mobil> updateMobil(@PathVariable int id, @RequestBody Mobil mobilDetails) throws MobilNotFoundException {
        Mobil updateMobil = service.get(id);
        updateMobil.setNama_mobil(mobilDetails.getNama_mobil());
        updateMobil.setNo_polisi(mobilDetails.getNo_polisi());
        updateMobil.setHarga_sewa(mobilDetails.getHarga_sewa());

        service.addMobil(updateMobil);

        return ResponseEntity.ok(updateMobil);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMobil(@PathVariable int id, @RequestBody Mobil mobilDetails) throws MobilNotFoundException {
        Mobil deleteMobil = service.get(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
