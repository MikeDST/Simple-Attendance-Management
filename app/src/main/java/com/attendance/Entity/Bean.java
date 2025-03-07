package com.attendance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Table(name ="beans")
public class Bean {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @Column
    private String name;

    //@JsonProperty(value="in_stock")
    @Column
    private boolean inStock;

    @Column
    private double price;

    @Column
    private int sold;

    //@JsonProperty(value="publish_date")
    @Column
    private LocalDateTime publishDate;

//    public Bean(String name, boolean inStock, double price, LocalDateTime publishDate) {
//        this.name = name;
//        this.inStock = inStock;
//        this.price = price;
//        this.publishDate = publishDate;
//    }

//    @Id
//    // @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public UUID getId() {
//        return id;
//    }

//    @Column(name = "name", nullable = false)
//    public String getName() {
//        return name;
//    }
//
//    @Column(name = "in_stock", nullable = false)
//    public boolean getInStock() {
//        return inStock;
//    }
//
//    @Column(name = "price", nullable = false)
//    public double getPrice() {
//        return price;
//    }
//
//    @Column(name = "publish_date", nullable = false)
//    public LocalDateTime getPublishDate() {
//        return publishDate;
//    }
//
//    @Override
//    public String toString() {
//        return "Bean [id=" + id + ", name=" + name + ", inStock=" + inStock + ", price=" + price + ", publishDate=" + publishDate + "]";
//    }
}
