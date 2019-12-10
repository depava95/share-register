package ua.biedin.register.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.biedin.register.entity.CompanyShare;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CompanySharePublicDTO {
    private Integer USREOU;           // ЄДРПОУ
    private Integer amount;
    private Double totalFaceValue;   // Загальна номінальна вартість
    private Double faceValue;        // Номінальна вартість
    private Timestamp releaseDate;

    public CompanySharePublicDTO(CompanyShare companyShare) {
        this.USREOU = companyShare.getUSREOU();
        this.amount = companyShare.getAmount();
        this.totalFaceValue = companyShare.getTotalFaceValue();
        this.faceValue = companyShare.getFaceValue();
        this.releaseDate = companyShare.getReleaseDate();
    }
}
