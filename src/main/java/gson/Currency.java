package gson;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Currency {
    private Integer id;
    private String Code;
    private String CcyNm_UZ;
    private Double Rate;
    private String Ccy;

    @SerializedName("Date")
    private Date sana;


}
