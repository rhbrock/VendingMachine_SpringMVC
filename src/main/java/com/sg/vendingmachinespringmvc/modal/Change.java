/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.modal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public class Change {

    private BigDecimal pennies;
    private BigDecimal nickels;
    private BigDecimal dimes;
    private BigDecimal quarters;

    public Change() {
        this.pennies = new BigDecimal(0);
        this.nickels = new BigDecimal(0);
        this.dimes = new BigDecimal(0);
        this.quarters = new BigDecimal(0);
    }

    public BigDecimal getPennies() {
        return pennies;
    }
    
    public void setPennies(BigDecimal pennies) {
        this.pennies = pennies;
    }

    public BigDecimal getNickels() {
        return nickels;
    }

    public void setNickels(BigDecimal nickels) {
        this.nickels = nickels;
    }

    public BigDecimal getDimes() {
        return dimes;
    }

    public void setDimes(BigDecimal dimes) {
        this.dimes = dimes;
    }

    public BigDecimal getQuarters() {
        return quarters;
    }

    public void setQuarters(BigDecimal quarters) {
        this.quarters = quarters;
    }
}
