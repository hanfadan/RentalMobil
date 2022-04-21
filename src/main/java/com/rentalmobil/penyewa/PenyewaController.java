package com.rentalmobil.penyewa;
import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.mobil.MobilRepository;
import com.rentalmobil.mobil.MobilService;
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
@RequestMapping("penyewa")

public class PenyewaController {
    @Autowired
    private PenyewaService penyewaService;

    @Autowired
    private PenyewaRepository penyewaRepository;

    @Autowired
    private MobilRepository mobilRepository;

    @Autowired
    private MobilService mobilService;

    //Melihat Tabel
    @RequestMapping("/")
    public ModelAndView showPenyewaList(ModelAndView modelAndView) {
        List<Penyewa> listPenyewa = penyewaService.listAll();
        modelAndView.addObject("listPenyewa", listPenyewa);
        modelAndView.setViewName("penyewa.html");
        return modelAndView;
    }

    //Mengambil semua Penyewa
    @GetMapping
    public ResponseEntity<List<PenyewaInfo>> getAllPenyewa() {
        List<PenyewaInfo> responsePenyewaList = new ArrayList<>();
        List<Penyewa> penyewaList = penyewaService.getAllPenyewa();
        for (int i = 0; i < penyewaList.size(); i++) {
            PenyewaInfo ob = new PenyewaInfo();
            BeanUtils.copyProperties(penyewaList.get(i), ob);
            responsePenyewaList.add(ob);
        }
        return new ResponseEntity<List<PenyewaInfo>>(responsePenyewaList, HttpStatus.OK);
    }

    //Mengambil Penyewa dengan ID
    @GetMapping("{id}")
    public ResponseEntity<Penyewa> getPenyewaById(@PathVariable Integer id) {
        System.out.println("test " + id);
        Penyewa penyewa = null;
        try {
            penyewa = penyewaService.get(id);
        } catch (PenyewaNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(penyewa);
    }

    //Membuat Penyewa Baru
    @PostMapping
    public ResponseEntity<Void> addPenyewa(@RequestBody PenyewaInfo penyewaInfo, UriComponentsBuilder builder) {
        Penyewa penyewa = new Penyewa();
        BeanUtils.copyProperties(penyewaInfo, penyewa);
        penyewaService.addPenyewa(penyewa);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/penyewa/{id}").buildAndExpand(penyewa.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Menampilkan form tambah penyewa
    @GetMapping("/new")
    public ModelAndView showNewForm(ModelAndView modelAndView) {
        List<Mobil> mobilList = (List<Mobil>) mobilRepository.findAll();
        modelAndView.addObject("penyewa", new Penyewa());
        modelAndView.addObject("mobilList", mobilList);
        modelAndView.addObject("pageTitle", "Tambah Penyewa");
        modelAndView.setViewName("penyewa_form.html");
        return modelAndView;
    }

    //Menyimpan data penyewa baru dari form
    @RequestMapping(value="/save", method=RequestMethod.POST)
    void savePenyewa (Penyewa penyewa, RedirectAttributes ra, HttpServletResponse response) throws IOException {
        penyewaService.addPenyewa(penyewa);
        ra.addFlashAttribute("message", "Penyewa berhasil ditambahkan.");
        response.sendRedirect("/penyewa/");
    }

    //Mengedit Penyewa melalui Form
    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    ModelAndView showEditForm(@PathVariable("id") Integer id, ModelAndView modelAndView, RedirectAttributes ra, HttpServletResponse response) throws IOException {
        try {
            Penyewa penyewa = penyewaService.get(id);
            modelAndView.addObject("penyewa", penyewa);
            modelAndView.addObject("pageTitle", "Edit Penyewa (ID: " + id +")");
            modelAndView.setViewName("penyewa_form.html");
            return modelAndView;
        } catch (PenyewaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            response.sendRedirect("/penyewa/");
        }
        return modelAndView;
    }

    //Menghapus Penyewa melalui Form
    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    void deletePenyewa(@PathVariable("id") Integer id, RedirectAttributes ra, HttpServletResponse response) throws IOException {
        try {
            penyewaService.delete(id);
            ra.addFlashAttribute("message", "penyewa dengan ID" + id + " telah dihapus.");
        } catch (PenyewaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        response.sendRedirect("/penyewa/");
    }

    //Mengedit Penyewa dengan API
    @PutMapping("{id}")
    public ResponseEntity<Penyewa> updatePenyewa(@PathVariable int id, @RequestBody Penyewa penyewaDetails) throws PenyewaNotFoundException {
        Penyewa updatePenyewa = penyewaService.get(id);
        updatePenyewa.setNama_penyewa(penyewaDetails.getNama_penyewa());
        updatePenyewa.setTanggal_mulai_sewa(penyewaDetails.getTanggal_mulai_sewa());
        updatePenyewa.setTanggal_selesai_sewa(penyewaDetails.getTanggal_selesai_sewa());
        updatePenyewa.setStatus(penyewaDetails.getStatus());
        updatePenyewa.setKeterangan(penyewaDetails.getKeterangan());


        penyewaService.addPenyewa(updatePenyewa);

        return ResponseEntity.ok(updatePenyewa);
    }

    //Menghapus Penyewa dengan API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePenyewa(@PathVariable int id) throws PenyewaNotFoundException {
        Optional<Penyewa> penyewa = penyewaRepository.findById(id);
        penyewaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
