package ua.biedin.register.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivateShareRequest {
    private String comment;
    private Integer capitalSize;
    private Integer usreou;
    private Integer amount;
    private BigDecimal faceValue;
    private BigDecimal stateDutyPaid;
}
