/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.modal;

import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Roger Brock
 */
public class VendingMachine {
    
    private int itemNumber;
    //@NotEmpty(message = "Must select a vending item before purchasing!")
    private String itemName;
    private BigDecimal itemPrice;
    private int itemQuantity;

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.itemNumber);
        hash = 53 * hash + Objects.hashCode(this.itemName);
        hash = 53 * hash + Objects.hashCode(this.itemPrice);
        hash = 53 * hash + Objects.hashCode(this.itemQuantity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendingMachine other = (VendingMachine) obj;
        if (!Objects.equals(this.itemNumber, other.itemNumber)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        if (!Objects.equals(this.itemQuantity, other.itemQuantity)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Item Number:" + itemNumber +
                " | Item Name:" + itemName +
                " | Item Price:" + itemPrice + 
                " | Item Quantity:" + itemQuantity;
    }

}
