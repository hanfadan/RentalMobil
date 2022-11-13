package com.rentalmobil.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class ScheduledJob {
//todo : 1. Hitung biaya per hari
// todo : 2. hitung denda per jam
// websocket

    @Scheduled(cron = "0 * * * * *")// detik , menit ,jam ,hari
    public void PerhitunganBiaya() {

        System.out.println("jalankan Ini " + new Date());
    }


}
