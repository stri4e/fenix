package com.github.products.entity;

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
@Table(name = "stocks_quantity", schema = "public")
public class StocksQuantity implements Serializable {

    private static final long serialVersionUID = 7346673027877099739L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Product.class
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_stock_fk"
            )
    )
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Stock.class
    )
    @JoinColumn(
            name = "stock_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "stock_product_fk"
            )
    )
    private Stock stock;

    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;

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
            nullable = false
    )
    private LocalDateTime updateAt;

    public StocksQuantity(Stock stock, Integer quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public StocksQuantity(Product product, Stock stock, Integer quantity) {
        this.product = product;
        this.stock = stock;
        this.quantity = quantity;
    }

    public StocksQuantity addStock(Stock stock) {
        this.stock = stock;
        this.stock.addLink(this);
        return this;
    }

    public StocksQuantity quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}
