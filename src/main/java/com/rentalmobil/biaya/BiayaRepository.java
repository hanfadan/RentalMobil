package com.rentalmobil.biaya;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BiayaRepository extends JpaRepository<Biaya, Integer> {
    public Integer countById(Integer id);
}
