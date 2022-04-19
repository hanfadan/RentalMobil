package com.rentalmobil.biaya;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentalmobil.mobil.Mobil;
import com.rentalmobil.penyewa.Penyewa;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter

public class BiayaInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date jam;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer biaya;
}
