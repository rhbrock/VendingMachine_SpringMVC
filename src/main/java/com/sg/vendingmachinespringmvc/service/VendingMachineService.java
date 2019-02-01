/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.modal.Change;
import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public interface VendingMachineService {

    //returns all items in the vending machine
    List<VendingMachine> getAllItems();

    /**
     * selects specific vending item by number
     *
     * @param itemNumber
     * @return
     *
     */
    VendingMachine selectItem(int itemNumber);

    void balance(String moneyType);

    BigDecimal getBalance();
    
    void clearBalance();
    
    String vendItem(int itemNumber, BigDecimal userMoney)
            throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInInventoryException;

    String makeChange(BigDecimal changeAmount);


}
