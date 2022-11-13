package com.rentalmobil.mobil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MobilInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nama_mobil;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String no_polisi;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String harga_sewa;
}
