package com.rentalmobil.penyewa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/penyewa")

public class PenyewaRESTController {
    @Autowired
    private PenyewaService service;

    @GetMapping
    public List<Penyewa> getAllPenyewa() { return service.listAll();}

    @PostMapping
    public Penyewa CreatePenyewa(@RequestBody Penyewa penyewa) {
        return service.savePenyewa(penyewa);
    }

    @PutMapping("{id}")
    public ResponseEntity<Penyewa> updatePenyewa(@PathVariable int id, @RequestBody Penyewa penyewaDetails) throws PenyewaNotFoundException {
        Penyewa updatePenyewa = service.get(id);
        updatePenyewa.setNama_penyewa(penyewaDetails.getNama_penyewa());
        updatePenyewa.setTanggal_mulai_sewa(penyewaDetails.getTanggal_mulai_sewa());
        updatePenyewa.setTanggal_selesai_sewa(penyewaDetails.getTanggal_selesai_sewa());
        updatePenyewa.setKeterangan(penyewaDetails.getKeterangan());
        updatePenyewa.setStatus(penyewaDetails.getStatus());

        service.savePenyewa(updatePenyewa);

        return ResponseEntity.ok(updatePenyewa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePenyewa(@PathVariable int id, @RequestBody Penyewa penyewaDetails) throws PenyewaNotFoundException {
        Penyewa deletePenyewa = service.get(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
