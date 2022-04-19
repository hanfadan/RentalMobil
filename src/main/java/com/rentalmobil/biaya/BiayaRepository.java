package com.rentalmobil.biaya;

import org.springframework.data.repository.CrudRepository;

public interface BiayaRepository extends CrudRepository<Biaya, Integer> {
    public Integer countById(Integer id);
}
