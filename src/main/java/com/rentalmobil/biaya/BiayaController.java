package com.rentalmobil.biaya;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("biaya")

public class BiayaController {

    @Autowired
    private BiayaService biayaService;

    //Melihat Tabel
    @RequestMapping("/")
    public ModelAndView showBiayaList(ModelAndView modelAndView) {
        List<Biaya> biayaList = biayaService.listAll();
        modelAndView.addObject("listBiaya", biayaList);
        modelAndView.setViewName("biaya.html");
        return modelAndView;
    }

    //Mengambil semua Biaya
    @GetMapping
    public ResponseEntity<List<BiayaInfo>> getAllBiaya() {
        List<BiayaInfo> responseBiayaList = new ArrayList<>();
        List<Biaya> biayaList = biayaService.getAllBiaya();
        for (int i = 0; i < biayaList.size(); i++) {
            BiayaInfo ob = new BiayaInfo();
            BeanUtils.copyProperties(biayaList.get(i), ob);
            responseBiayaList.add(ob);
        }
        return new ResponseEntity<List<BiayaInfo>>(responseBiayaList, HttpStatus.OK);
    }

    //Mengambil Biaya dengan ID
    @GetMapping("{id}")
    public ResponseEntity<Biaya> getBiayaById(@PathVariable Integer id) {
        System.out.println("test " + id);
        Biaya biaya = null;
        try {
            biaya = biayaService.get(id);
        } catch (BiayaNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(biaya);
    }

    //Membuat Biaya Baru
    @PostMapping
    public ResponseEntity<Void> addBiaya(@RequestBody BiayaInfo biayaInfo, UriComponentsBuilder builder) {
        Biaya biaya = new Biaya();
        BeanUtils.copyProperties(biayaInfo, biaya);
        biayaService.addBiaya(biaya);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/biaya/{id}").buildAndExpand(biaya.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Mengedit Biaya
    @PutMapping("{id}")
    public ResponseEntity<Void> updateBiaya(@PathVariable Integer id, @RequestBody BiayaInfo biayaInfo, UriComponentsBuilder builder) throws BiayaNotFoundException {
        Biaya updateBiaya = biayaService.get(id);
        updateBiaya.setJam(biayaInfo.getJam());
        updateBiaya.setBiaya(biayaInfo.getBiaya());
        biayaService.addBiaya(updateBiaya);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/biaya/{id}").buildAndExpand(updateBiaya.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    //Menghapus Biaya
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBiaya(@PathVariable Integer id, @RequestBody BiayaInfo biayaInfo, UriComponentsBuilder builder) throws BiayaNotFoundException {
        Biaya deleteBiaya = biayaService.get(id);
        biayaService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/biaya/{id}").buildAndExpand(deleteBiaya.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
    }
}
