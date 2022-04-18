package com.rentalmobil.penyewa;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.biaya.BiayaService;
import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.mobil.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class PenyewaController {
    @Autowired private PenyewaService service;

    @Autowired private MobilService mobilService;

    @GetMapping("/penyewa")
    public String showPenyewaList(Model model){
        List<Penyewa> listPenyewa = service.listAll();
        model.addAttribute("listPenyewa", listPenyewa);
        return "penyewa";
    }

    @GetMapping("/penyewa/new")
    public String showNewForm(Model model) {
        List<Mobil> mobilList = mobilService.listAll();

        model.addAttribute("penyewa", new Penyewa());
        model.addAttribute("mobilList", mobilList);
        model.addAttribute("pageTitle", "Tambah Penyewa");
        return "penyewa_form";
    }

    @PostMapping("/penyewa/save")
    public String savePenyewa(Penyewa penyewa, RedirectAttributes ra) {
        service.savePenyewa(penyewa);
        ra.addFlashAttribute("message", "Penyewa berhasil ditambahkan.");
        return "redirect:/penyewa";
    }

    @GetMapping("/penyewa/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Penyewa penyewa = service.get(id);
            model.addAttribute("penyewa", penyewa);
            model.addAttribute("pageTitle", "Edit Penyewa (ID: " + id +")");
            return "penyewa_form";
        } catch (PenyewaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/penyewa";
        }
    }

    @GetMapping("/penyewa/delete/{id}")
    public String deletePenyewa(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "penyewa dengan ID" + id + " telah dihapus.");
        } catch (PenyewaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/penyewa";
    }
}
