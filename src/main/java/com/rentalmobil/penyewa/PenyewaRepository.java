package com.rentalmobil.penyewa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PenyewaRepository extends JpaRepository<Penyewa, Integer> {
    public Integer countById(Integer id);
}
