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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    private double moneyCount = 0;

    private VendingMachineDao dao;

    @Inject
    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public List<VendingMachine> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public VendingMachine selectItem(int itemNumber) {
        return dao.selectItem(itemNumber);
    }

    @Override
    public void balance(String moneyType) {

        switch (moneyType) {
            case "dollar":
                moneyCount += 1.00;
                break;
            case "quarter":
                moneyCount += 0.25;
                break;
            case "dime":
                moneyCount += 0.10;
                break;
            case "nickel":
                moneyCount += 0.05;
                break;
        }
    }

    @Override
    public BigDecimal getBalance() {
        BigDecimal amountOfMoney = new BigDecimal(moneyCount)
                .setScale(2, RoundingMode.HALF_DOWN);

        return amountOfMoney;
    }

    @Override
    public void clearBalance() {
        moneyCount = 0;
    }

    @Override
    public String vendItem(int itemNumber, BigDecimal userMoney)
            throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInInventoryException {

        if (dao.selectItem(itemNumber).getItemQuantity() <= 0) {
            throw new VendingMachineNoItemInInventoryException(
                    "Sold out!");
        }

        BigDecimal amountNeeded = userMoney.subtract(dao.selectItem(itemNumber)
                .getItemPrice());
        int checkForInsufficientFunds
                = (userMoney.compareTo(dao.selectItem(itemNumber)
                        .getItemPrice()));
        if (checkForInsufficientFunds == -1) {
            throw new VendingMachineInsufficientFundsException("Please Deposit: $" + amountNeeded.abs());
        }

        //decrease item quantity
        int selectedItemQuantity = dao.selectItem(itemNumber).getItemQuantity();
        int decreaseQuantity = selectedItemQuantity - 1;
        dao.selectItem(itemNumber).setItemQuantity(decreaseQuantity);
        dao.updateItem(dao.selectItem(itemNumber));

        return makeChange(amountNeeded);
    }

    @Override
    public String makeChange(BigDecimal changeAmount) {

        Change userChange = new Change();

        BigDecimal changeRemaining = new BigDecimal(0);
        final BigDecimal forQuarters = new BigDecimal(0.25);
        final BigDecimal forDimes = new BigDecimal(0.10);
        final BigDecimal forNickels = new BigDecimal(0.05);
        final BigDecimal forPennies = new BigDecimal(0.01);

        BigDecimal quarters = changeAmount.setScale(2, RoundingMode.HALF_UP)
                .divide(forQuarters);
        userChange.setQuarters(quarters.setScale(0, RoundingMode.DOWN));
        changeRemaining = changeAmount.setScale(2, RoundingMode.HALF_UP)
                .remainder(forQuarters.setScale(2, RoundingMode.HALF_UP));

        BigDecimal dimes = changeRemaining.divide(forDimes.setScale(2, RoundingMode.HALF_UP));
        userChange.setDimes(dimes.setScale(0, RoundingMode.DOWN));
        changeRemaining = changeRemaining.remainder(forDimes
                .setScale(2, RoundingMode.HALF_UP));

        BigDecimal nickels = changeRemaining.divide(forNickels
                .setScale(2, RoundingMode.HALF_UP));
        userChange.setNickels(nickels.setScale(0, RoundingMode.DOWN));
        changeRemaining = changeRemaining.remainder(forNickels
                .setScale(2, RoundingMode.HALF_UP));

        BigDecimal pennies = changeRemaining.divide(forPennies
                .setScale(2, RoundingMode.HALF_UP));
        userChange.setPennies(pennies.setScale(0, RoundingMode.DOWN));

        String returnChange = "";

        int q = userChange.getQuarters().intValue();
        int d = userChange.getDimes().intValue();
        int n = userChange.getNickels().intValue();
        int p = userChange.getPennies().intValue();

        if (q != 0) {
            returnChange += userChange.getQuarters().toString() + " Quarters ";
        }
        if (d != 0) {
            returnChange += userChange.getDimes().toString() + " Dimes ";
        }
        if (n != 0) {
            returnChange += userChange.getNickels().toString() + " Nickels ";
        }
        if (p != 0) {
            returnChange += userChange.getPennies().toString() + " Pennies ";
        }

        return returnChange;

    }
}
