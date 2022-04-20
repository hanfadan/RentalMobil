package com.rentalmobil.penyewa;

import org.springframework.data.repository.CrudRepository;

public interface PenyewaRepository extends CrudRepository<Penyewa, Integer> {
    public Integer countById(Integer id);
}
