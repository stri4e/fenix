package com.github.deliveries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "novaposta_internet_document", schema = "public")
public class NovaposhtaInternetDocument implements Serializable, Cloneable {

    private static final long serialVersionUID = 8086003987288000485L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "ref",
            nullable = false
    )
    private String ref;

    @Column(
            name = "cost_on_size",
            nullable = false
    )
    private Integer costOnSize;

    @Column(
            name = "estimated_delivery_date",
            nullable = false
    )
    private String estimatedDeliveryDate;

    @Column(
            name = "int_doc_number",
            nullable = false
    )
    private String intDocNumber;

    @Column(
            name = "type_document",
            nullable = false
    )
    private String typeDocument;

    @Column(
            name = "order_id",
            nullable = false
    )
    private Long orderId;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @CreationTimestamp
    @Column(
            name = "create_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(
            name = "update_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime updateAt;

    public NovaposhtaInternetDocument(
            Long id,
            String ref,
            Integer costOnSize,
            String estimatedDeliveryDate,
            String intDocNumber,
            String typeDocument,
            Long orderId) {
        this.id = id;
        this.ref = ref;
        this.costOnSize = costOnSize;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.intDocNumber = intDocNumber;
        this.typeDocument = typeDocument;
        this.orderId = orderId;
    }

}
