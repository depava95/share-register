package ua.biedin.register.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.biedin.register.entity.CompanyShare;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PublicShareResponse {

    private Integer USREOU;           // ЄДРПОУ
    private Integer amount;
    private Double totalFaceValue;   // Загальна номінальна вартість
    private Double faceValue;        // Номінальна вартість
    private Timestamp releaseDate;   // Дата випуску


    public PublicShareResponse(CompanyShare companyShare) {
        this.USREOU = companyShare.getUSREOU();
        this.amount = companyShare.getAmount();
        this.totalFaceValue = companyShare.getTotalFaceValue();
        this.faceValue = companyShare.getFaceValue();
        this.releaseDate = companyShare.getReleaseDate();
    }
}
