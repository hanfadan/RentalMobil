package com.rentalmobil.mobil;

import org.springframework.data.repository.CrudRepository;

public interface MobilRepository extends CrudRepository<Mobil, Integer> {
    public Integer countById(Integer id);
}
