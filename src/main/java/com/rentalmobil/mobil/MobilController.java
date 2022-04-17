package com.rentalmobil.mobil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class MobilController {
    @Autowired
    private MobilService service;

    @GetMapping("/mobil")
    public String showMobilList(Model model){
        List<Mobil> listMobil = service.listAll();
        model.addAttribute("listMobil", listMobil);
        return "mobil";
    }

    @GetMapping("/mobil/new")
    public String showNewForm(Model model) {
        model.addAttribute("mobil", new Mobil());
        model.addAttribute("pageTitle", "Tambah Mobil");
        return "mobil_form";
    }

    @PostMapping("/mobil/save")
    public String saveMobil(Mobil mobil, RedirectAttributes ra) {
        service.saveMobil(mobil);
        ra.addFlashAttribute("message", "Mobil berhasil ditambahkan.");
        return "redirect:/mobil";
    }

    @GetMapping("/mobil/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Mobil mobil = service.get(id);
            model.addAttribute("mobil", mobil);
            model.addAttribute("pageTitle", "Edit Mobil (ID: " + id +")");
            return "mobil_form";
        } catch (MobilNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/mobil";
        }
    }

    @GetMapping("/mobil/delete/{id}")
    public String deleteMobil(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "mobil dengan ID" + id + " telah dihapus.");
        } catch (MobilNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/mobil";
    }
}
