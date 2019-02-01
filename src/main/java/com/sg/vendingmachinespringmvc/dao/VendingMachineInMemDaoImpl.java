/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roger Brock
 */
public class VendingMachineInMemDaoImpl implements VendingMachineDao {

    private Map<Integer, VendingMachine> vMap = new HashMap<>();

    public VendingMachineInMemDaoImpl() {

        VendingMachine vm = new VendingMachine();
        vm.setItemNumber(1);
        vm.setItemName("Chips");
        vm.setItemPrice(new BigDecimal("1.50"));
        vm.setItemQuantity(2);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(2);
        vm.setItemName("Lil' Cakes");
        vm.setItemPrice(new BigDecimal("2.00"));
        vm.setItemQuantity(10);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(3);
        vm.setItemName("Crackers");
        vm.setItemPrice(new BigDecimal("0.75"));
        vm.setItemQuantity(10);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(4);
        vm.setItemName("Snicks");
        vm.setItemPrice(new BigDecimal("1.35"));
        vm.setItemQuantity(1);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(5);
        vm.setItemName("3 Pirates");
        vm.setItemPrice(new BigDecimal("1.25"));
        vm.setItemQuantity(10);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(6);
        vm.setItemName("Sugar Ballz");
        vm.setItemPrice(new BigDecimal("0.95"));
        vm.setItemQuantity(1);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(7);
        vm.setItemName("Hard Candy");
        vm.setItemPrice(new BigDecimal("0.50"));
        vm.setItemQuantity(10);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(8);
        vm.setItemName("Soft Candy");
        vm.setItemPrice(new BigDecimal("1.00"));
        vm.setItemQuantity(10);
        vMap.put(vm.getItemNumber(), vm);

        vm = new VendingMachine();
        vm.setItemNumber(9);
        vm.setItemName("Gum");
        vm.setItemPrice(new BigDecimal("0.50"));
        vm.setItemQuantity(10);
        vMap.put(vm.getItemNumber(), vm);

    }

    @Override
    public List<VendingMachine> getAllItems() {
        Collection <VendingMachine> v = vMap.values();
        return new ArrayList(v);
        //return new ArrayList<VendingMachine>(vMap.values());
    }

    @Override
    public VendingMachine selectItem(int itemNumber) {
        VendingMachine item = vMap.get(itemNumber);
        return item;
    }

    @Override
    public void updateItem(VendingMachine item) {
        vMap.put(item.getItemNumber(), item);
    }

    //Used in unit testing only
    @Override
    public void addItem(VendingMachine item) {
        
    }

}
