package com.rentalmobil;

import com.rentalmobil.penyewa.Penyewa;
import com.rentalmobil.penyewa.PenyewaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PenyewaRepositoryTest {
    @Autowired private PenyewaRepository repo;

    @Test
    public void testAddNew(){
        Penyewa penyewa = new Penyewa();
        penyewa.setNama_penyewa("Farhan");
        penyewa.setKeterangan("Aktif");
        penyewa.setTanggal_mulai_sewa(LocalDate.parse("2022-01-10"));
        penyewa.setTanggal_selesai_sewa(LocalDate.parse("2022-03-10"));

        Penyewa savePenyewa = repo.save(penyewa);

        Assertions.assertThat(savePenyewa).isNotNull();
        Assertions.assertThat(savePenyewa.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Penyewa> penyewas = repo.findAll();
        Assertions.assertThat(penyewas).hasSizeGreaterThan(0);

        for (Penyewa penyewa : penyewas) {
            System.out.println(penyewa);
        }
    }



    @Test
    public void testUpdate() {
        Integer penyewaId = 1;
        Optional <Penyewa> optionalPenyewa = repo.findById(penyewaId);
        Penyewa penyewa = optionalPenyewa.get();
        penyewa.setNama_penyewa("hans");
        repo.save(penyewa);

        Penyewa updatedPenyewa = repo.findById(penyewaId).get();
        Assertions.assertThat(updatedPenyewa.getNama_penyewa()).isEqualTo("hans");
    }

    @Test
    public void testGet() {
        Integer penyewaId = 2;
        Optional<Penyewa> optionalPenyewa = repo.findById(penyewaId);
        Assertions.assertThat(optionalPenyewa).isPresent();
        System.out.println(optionalPenyewa.get());
    }

    @Test
    public void testDelete() {
        Integer penyewaId = 2;
        repo.deleteById(penyewaId);

        Optional<Penyewa> optionalPenyewa = repo.findById(penyewaId);
        Assertions.assertThat(optionalPenyewa).isNotPresent();

    }
}
