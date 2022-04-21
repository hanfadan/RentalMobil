package com.rentalmobil.mobil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("mobil")

public class MobilController {
    @Autowired
    private MobilService mobilservice;

    @Autowired
    private MobilRepository mobilRepository;

    //Melihat Tabel
    @RequestMapping("/")
    public ModelAndView showMobilList(ModelAndView modelAndView) {
        List<Mobil> mobilList = mobilservice.listAll();
        modelAndView.addObject("listMobil", mobilList);
        modelAndView.setViewName("mobil.html");
        return modelAndView;
    }

    //Mengambil semua Mobil
    @GetMapping
    public ResponseEntity<List<MobilInfo>> getAllMobil() {
        List<MobilInfo> responseMobilList = new ArrayList<>();
        List<Mobil> mobilList = mobilservice.getAllMobil();
        for (int i = 0; i < mobilList.size(); i++) {
            MobilInfo ob = new MobilInfo();
            BeanUtils.copyProperties(mobilList.get(i), ob);
            responseMobilList.add(ob);
        }
        return new ResponseEntity<List<MobilInfo>>(responseMobilList, HttpStatus.OK);
    }

    //Mengambil Mobil dengan ID
    @GetMapping("{id}")
    public ResponseEntity<Mobil> getMobilById(@PathVariable Integer id) {
        System.out.println("test " + id);
        Mobil mobil = null;
        try {
            mobil = mobilservice.get(id);
        } catch (MobilNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(mobil);
    }

    //Membuat Mobil Baru
    @PostMapping
    public ResponseEntity<Void> addMobil(@RequestBody MobilInfo mobilInfo, UriComponentsBuilder builder) {
        Mobil mobil = new Mobil();
        BeanUtils.copyProperties(mobilInfo, mobil);
        mobilservice.addMobil(mobil);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/mobil/{id}").buildAndExpand(mobil.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Menampilkan form tambah mobil
    @GetMapping("/new")
    public ModelAndView showNewForm(ModelAndView modelAndView) {
        modelAndView.addObject("mobil", new Mobil());
        modelAndView.addObject("pageTitle", "Tambah Mobil");
        modelAndView.setViewName("mobil_form.html");
        return modelAndView;
    }

    //Menyimpan data mobil baru dari form
    @RequestMapping(value="/save", method=RequestMethod.POST)
    void saveMobil(Mobil mobil, RedirectAttributes ra, HttpServletResponse response) throws IOException {
        mobilservice.addMobil(mobil);
        ra.addFlashAttribute("message", "Mobil berhasil ditambahkan.");
        response.sendRedirect("/mobil/");
    }

    //Mengedit Mobil melalui Form
    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    ModelAndView showEditForm(@PathVariable("id") Integer id, ModelAndView modelAndView, RedirectAttributes ra, HttpServletResponse response) throws IOException {
        try {
            Mobil mobil = mobilservice.get(id);
            modelAndView.addObject("mobil", mobil);
            modelAndView.addObject("pageTitle", "Edit Mobil (ID: " + id +")");
            modelAndView.setViewName("mobil_form.html");
            return modelAndView;
        } catch (MobilNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            response.sendRedirect("/mobil/");
        }
        return modelAndView;
    }

    //Menghapus Mobil melalui Form
    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    void deleteMobil(@PathVariable("id") Integer id, RedirectAttributes ra, HttpServletResponse response) throws IOException {
        try {
            mobilservice.delete(id);
            ra.addFlashAttribute("message", "mobil dengan ID" + id + " telah dihapus.");
        } catch (MobilNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        response.sendRedirect("/mobil/");
    }

    //Mengedit Mobil dengan API
    @PutMapping("{id}")
    public ResponseEntity<Mobil> updateMobil(@PathVariable int id, @RequestBody Mobil mobilDetails) throws MobilNotFoundException {
        Mobil updateMobil = mobilservice.get(id);
        updateMobil.setNama_mobil(mobilDetails.getNama_mobil());
        updateMobil.setNo_polisi(mobilDetails.getNo_polisi());
        updateMobil.setHarga_sewa(mobilDetails.getHarga_sewa());

        mobilservice.addMobil(updateMobil);

        return ResponseEntity.ok(updateMobil);
    }

    //Menghapus Mobil dengan API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMobil(@PathVariable int id) throws MobilNotFoundException {
        Optional<Mobil> mobil = mobilRepository.findById(id);
        mobilRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
