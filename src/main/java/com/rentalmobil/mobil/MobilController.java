package com.rentalmobil.mobil;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.biaya.BiayaInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mobil")

public class MobilController {
    @Autowired
    private MobilService mobilservice;

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

    @GetMapping("/mobil")
    public String showMobilList(Model model){
        List<Mobil> listMobil = mobilservice.listAll();
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
        mobilservice.addMobil(mobil);
        ra.addFlashAttribute("message", "Mobil berhasil ditambahkan.");
        return "redirect:/mobil";
    }

    @GetMapping("/mobil/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Mobil mobil = mobilservice.get(id);
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
            mobilservice.delete(id);
            ra.addFlashAttribute("message", "mobil dengan ID" + id + " telah dihapus.");
        } catch (MobilNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/mobil";
    }
}
