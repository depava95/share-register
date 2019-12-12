package ua.biedin.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "company_share")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
public class CompanyShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "comment")
    private String comment;
    /**
     * Розмір статутного капіталу
     */
    @Column(name = "capital_size")
    private Integer capitalSize;
    /**
     * ЄДРПОУ
     */
    @Column(name = "usreou")
    private Integer usreou;
    @Column(name = "amount")
    private Integer amount;
    /**
     * Загальна номінальна вартість
     */
    @Column(name = "total_value", precision = 13, scale = 4)
    private BigDecimal totalFaceValue;
    /**
     * Номінальна вартість
     */
    @Column(name = "face_value", precision = 13, scale = 4)
    private BigDecimal faceValue;
    /**
     * Сплачене державне мито
     */
    @Column(name = "duty_paid", precision = 13, scale = 4)
    private BigDecimal stateDutyPaid;
    @CreationTimestamp
    @Column(name = "release_date")
    private Timestamp releaseDate;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_deleted")
    private boolean isDelete;
}
