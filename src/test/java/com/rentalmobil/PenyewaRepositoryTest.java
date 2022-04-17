package com.rentalmobil;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.biaya.BiayaRepository;
import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.mobil.MobilRepository;
import com.rentalmobil.penyewa.Penyewa;
import com.rentalmobil.penyewa.PenyewaRepository;
import com.rentalmobil.penyewa.PenyewaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PenyewaRepositoryTest {

    private PenyewaRepository penyewaRepository;
    private MobilRepository mobilRepository;
    private BiayaRepository biayaRepository;

    @Autowired
    public void Repo(PenyewaRepository penyewaRepository, MobilRepository mobilRepository, BiayaRepository biayaRepository){
        this.penyewaRepository = penyewaRepository;
        this.mobilRepository = mobilRepository;
        this.biayaRepository = biayaRepository;
    }

    @Test
    public void testAddNew(){
        Penyewa penyewa = new Penyewa();
        penyewa.setNama_penyewa("Farhan");
        penyewa.setTanggal_mulai_sewa(new Date());
        penyewa.setTanggal_selesai_sewa(new Date());
        penyewa.setKeterangan("Aktif");
        penyewa.setStatus(true);

        Penyewa savePenyewa = penyewaRepository.save(penyewa);

        Assertions.assertThat(savePenyewa).isNotNull();
        Assertions.assertThat(savePenyewa.getId()).isGreaterThan(0);

        Biaya biaya = new Biaya();
        biaya.setJam(new Date());
        biaya.setBiaya(100000);

        Biaya saveBiaya = biayaRepository.save(biaya);

        Assertions.assertThat(saveBiaya).isNotNull();
        Assertions.assertThat(saveBiaya.getId()).isGreaterThan(0);

        Mobil mobil = new Mobil();
        mobil.setNama_mobil("Honda Maestro");
        mobil.setNo_polisi("B279KW");
        mobil.setHarga_sewa("100000");

        Mobil saveMobil = mobilRepository.save(mobil);

        Assertions.assertThat(saveMobil).isNotNull();
        Assertions.assertThat(saveMobil.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Penyewa> penyewas = penyewaRepository.findAll();
        Assertions.assertThat(penyewas).hasSizeGreaterThan(0);

        for (Penyewa penyewa : penyewas) {
            System.out.println(penyewa);
        }
    }



    @Test
    public void testUpdate() {
        Integer penyewaId = 1;
        Optional <Penyewa> optionalPenyewa = penyewaRepository.findById(penyewaId);
        Penyewa penyewa = optionalPenyewa.get();
        penyewa.setNama_penyewa("hans");
        penyewaRepository.save(penyewa);

        Penyewa updatedPenyewa = penyewaRepository.findById(penyewaId).get();
        Assertions.assertThat(updatedPenyewa.getNama_penyewa()).isEqualTo("hans");
    }

    @Test
    public void testGet() {
        Integer penyewaId = 2;
        Optional<Penyewa> optionalPenyewa = penyewaRepository.findById(penyewaId);
        Assertions.assertThat(optionalPenyewa).isPresent();
        System.out.println(optionalPenyewa.get());
    }

    @Test
    public void testDelete() {
        Integer penyewaId = 2;
        penyewaRepository.deleteById(penyewaId);

        Optional<Penyewa> optionalPenyewa = penyewaRepository.findById(penyewaId);
        Assertions.assertThat(optionalPenyewa).isNotPresent();

    }
}
