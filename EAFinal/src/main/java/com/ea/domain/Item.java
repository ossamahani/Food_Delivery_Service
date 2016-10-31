/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ea.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "item", catalog = "food_delivery")
public class Item implements java.io.Serializable {

    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private byte[] itemImage;
    @NotNull
    @Min(1)
    private Integer price;
    private Business business;
    private String status;

    private Set<OrderGroup> orderGroups = new HashSet<OrderGroup>(0);

    public Item() {
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(Business business, String name, Integer price, String status, int id, String description,
                byte[] itemImage, Set<OrderGroup> orderGroups) {
        this.business = business;
        this.name = name;
        this.price = price;
        this.status = status;
        this.id = id;
        this.description = description;
        this.itemImage = itemImage;
        this.orderGroups = orderGroups;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businessId")
    public Business getBusiness() {
        return this.business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "status", length = 20)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "item_image")
    public byte[] getItemImage() {
        return this.itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    public Set<OrderGroup> getOrderGroups() {
        return this.orderGroups;
    }

    public void setOrderGroups(Set<OrderGroup> orderGroups) {
        this.orderGroups = orderGroups;
    }

}
