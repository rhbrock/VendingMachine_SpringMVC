/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roger
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    
    VendingMachine vm;
    private Map<Integer, VendingMachine> vMap = new HashMap<>();
    
    public VendingMachineDaoStubImpl(){
        vm = new VendingMachine();
        vm.setItemNumber(1);
        vm.setItemName("Test");
        vm.setItemPrice(new BigDecimal("1.50"));
        vm.setItemQuantity(1);
        
        vMap.put(vm.getItemNumber(), vm);

    }

    @Override
    public List<VendingMachine> getAllItems(){
        return new ArrayList<VendingMachine>(vMap.values());
    }


    @Override
    public VendingMachine selectItem(int itemNumber) {
                if (itemNumber == vm.getItemNumber()){
            return vm;
        }else{
            return null;
        }
    }

    @Override
    public void updateItem(VendingMachine item) {
        //no return
    }

    @Override
    public void addItem(VendingMachine item) {
        //only used for dao unit testing
    }
    
}
