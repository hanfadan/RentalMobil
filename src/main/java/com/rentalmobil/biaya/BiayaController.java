package com.rentalmobil.biaya;

import com.rentalmobil.penyewa.Penyewa;
import com.rentalmobil.penyewa.PenyewaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class BiayaController {
    @Autowired
    private BiayaService service;

    @GetMapping("/biaya")
    public String showBiayaList(Model model){
        List<Biaya> listBiaya = service.listAll();
        model.addAttribute("listBiaya", listBiaya);
        return "biaya";
    }

    @GetMapping("{id}")
    public ResponseEntity<Biaya> getBiayaById(@PathVariable("id") Integer id, RedirectAttributes ra) throws BiayaNotFoundException {
        Biaya biaya = service.get(id);
        return ResponseEntity.ok(biaya);
    }

    @GetMapping("/biaya/new")
    public String showNewForm(Model model) {
        model.addAttribute("biaya", new Biaya());
        model.addAttribute("pageTitle", "Tambah Biaya");
        return "biaya_form";
    }

    @PostMapping("/biaya/save")
    public String saveBiaya(Biaya biaya, RedirectAttributes ra) {
        service.saveBiaya(biaya);
        ra.addFlashAttribute("message", "Biaya berhasil ditambahkan.");
        return "redirect:/biaya";
    }

    @GetMapping("/biaya/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Biaya biaya = service.get(id);
            model.addAttribute("biaya", biaya);
            model.addAttribute("pageTitle", "Edit Biaya (ID: " + id +")");
            return "biaya_form";
        } catch (BiayaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/biaya";
        }
    }

    @GetMapping("/biaya/delete/{id}")
    public String deleteBiaya(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "biaya dengan ID" + id + " telah dihapus.");
        } catch (BiayaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/biaya";
    }
}
