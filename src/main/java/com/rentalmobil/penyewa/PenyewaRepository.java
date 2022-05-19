package com.rentalmobil.penyewa;

import com.rentalmobil.biaya.Biaya;
import com.rentalmobil.mobil.Mobil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.websocket.server.PathParam;

public interface PenyewaRepository extends JpaRepository<Penyewa, Integer> {
    public Integer countById(Integer id);

/*    @Modifying
    @Query("UPDATE Biaya p SET p.id_penyewa = :id WHERE p.id_penyewa = :id")
    public Penyewa addIdPenyewa(@PathParam("id") Integer id, @Param("id_penyewa") Integer id_penyewa);*/
}
