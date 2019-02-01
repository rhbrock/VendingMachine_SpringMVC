/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface VendingMachineDao {
    
    //returns all items in the vending machine
    List<VendingMachine> getAllItems(); 

    /**
     * selects specific vending item by number
     * @param itemNumber
     * @return
     * 
     */
    VendingMachine selectItem(int itemNumber);

    //updates item quantity
    void updateItem(VendingMachine item);
    
    //for unit testing
    void addItem(VendingMachine item);

}
