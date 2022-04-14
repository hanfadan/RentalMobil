package com.rentalmobil.penyewa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class PenyewaController {
    @Autowired private PenyewaService service;

    @GetMapping("/penyewa")
    public String showPenyewaList(Model model){
        List<Penyewa> listPenyewa = service.listAll();
        model.addAttribute("listPenyewa", listPenyewa);
        return "penyewa";
    }

    @GetMapping("/penyewa/new")
    public String showNewForm(Model model) {
        model.addAttribute("penyewa", new Penyewa());
        return "penyewa_form";
    }
}
