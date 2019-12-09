package ua.biedin.register.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity

@Table(name = "company_share")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "capital_size")
    private Integer capitalSize; // Розмір статутного капіталу
    @Column(name = "usreou")
    private Integer USREOU; //ЄДРПОУ
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "total_value")
    private Double totalFaceValue; //Загальна номінальна вартість
    @Column(name = "face_value")
    private Double faceValue; // Номінальна вартість
    @Column(name = "duty_paid")
    private Double stateDutyPaid; // Сплачене державне мито
    @CreationTimestamp
    @Column(name = "release_date")
    private Timestamp releaseDate;


}
