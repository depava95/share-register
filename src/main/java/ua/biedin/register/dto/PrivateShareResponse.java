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
public class PrivateShareResponse {
    private Long id;
    private String comment;
    private Integer capitalSize;
    private Integer USREOU;
    private Integer amount;
    private Double totalFaceValue;
    private Double faceValue;
    private Double stateDutyPaid;
    private Timestamp releaseDate;

    public PrivateShareResponse(CompanyShare companyShare) {
        this.USREOU = companyShare.getUSREOU();
        this.amount = companyShare.getAmount();
        this.totalFaceValue = companyShare.getTotalFaceValue();
        this.faceValue = companyShare.getFaceValue();
        this.releaseDate = companyShare.getReleaseDate();
        this.id = companyShare.getId();
        this.comment = companyShare.getComment();
        this.capitalSize = companyShare.getCapitalSize();
        this.stateDutyPaid = companyShare.getStateDutyPaid();
    }
}
