package com.bdran.InventorySystem.model;

import javax.persistence.*;

@Entity
@Table(name = "Inventory_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    @Column(name = "item_quantity")
    private int quantity;
    @Column(name = "item_price")
    private double price;
    @Column(name = "item_fine_rate")
    private double fineRate;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_invoice_number")
    private long invoiceNumber;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_fk")
    private ItemType itemType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id_fk")
    private Vendor vendor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFineRate() {
        return fineRate;
    }

    public void setFineRate(double fineRate) {
        this.fineRate = fineRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

}
