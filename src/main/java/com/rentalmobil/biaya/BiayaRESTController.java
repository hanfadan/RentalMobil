package com.rentalmobil.biaya;

import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.mobil.MobilNotFoundException;
import com.rentalmobil.mobil.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/v1/biaya")

public class BiayaRESTController {
    @Autowired
    private BiayaService service;

    @GetMapping
    public List<Biaya> getAllBiaya() {
        return service.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Biaya> getBiayaById(@PathVariable Integer id) {
        System.out.println("test " + id);
        Biaya biaya = null;
        try {
            biaya = service.get(id);
        } catch (BiayaNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(biaya);
    }

    @PostMapping
    public Biaya CreateBiaya(@RequestBody Biaya biaya) {
        return service.saveBiaya(biaya);
    }

    @PutMapping("{id_penyewa}")
    public ResponseEntity<Biaya> updateBiaya(@PathVariable int id_penyewa, @RequestBody Biaya biayaDetails) throws BiayaNotFoundException {
        Biaya updateBiaya = service.get(id_penyewa);
        updateBiaya.setJam(biayaDetails.getJam());
        updateBiaya.setBiaya(biayaDetails.getBiaya());

        service.saveBiaya(updateBiaya);

        return ResponseEntity.ok(updateBiaya);
    }

    @DeleteMapping("{id_penyewa}")
    public ResponseEntity<HttpStatus> deleteBiaya(@PathVariable int id_penyewa, @RequestBody Biaya biayaDetails) throws BiayaNotFoundException, BiayaNotFoundException {
        Biaya deleteBiaya = service.get(id_penyewa);
        service.delete(id_penyewa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
