package com.rentalmobil.penyewa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentalmobil.mobil.Mobil;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter

public class PenyewaInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nama_penyewa;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date tanggal_mulai_sewa;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date tanggal_selesai_sewa;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String keterangan;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Mobil mobil;
}
