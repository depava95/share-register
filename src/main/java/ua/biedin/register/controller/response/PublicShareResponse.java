package ua.biedin.register.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PublicShareResponse {
    private Long id;
    private Integer usreou;
    private Integer amount;
    private BigDecimal totalFaceValue;
    private BigDecimal faceValue;
    private Timestamp releaseDate;
}
