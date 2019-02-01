/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.modal.Change;
import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineNoItemInInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Roger Brock
 */
@Controller
public class VendingMachineController {

    private VendingMachineService service;

    @Inject
    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }

    //used @GetMapping instead of @RequestMapping with provided HTTP method
    @GetMapping(value = "/displayVendingPage")
    public String getAllItems(HttpServletRequest request, Model model) {

        //load items
        List<VendingMachine> vendingItems = service.getAllItems();
        model.addAttribute("vendingItems", vendingItems);

        //show total
        BigDecimal totalMoney = service.getBalance();
        model.addAttribute("balance", totalMoney);

        //show selected item
        String selectedItemNumber = request.getParameter("itemNumber");
        model.addAttribute("itemNumber", selectedItemNumber);

        //purchase
        String returnedChange = request.getParameter("change");
        model.addAttribute("change", returnedChange);
        String message = request.getParameter("itemMessage");
        model.addAttribute("itemMessage", message);

        return "vendingPage";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request) {

        // track total money in vending machine
        service.balance(request.getParameter("moneyType"));

        //attempt to pass itemNumber back to main view... failed
        String itemNumber = request.getParameter("itemNumber");
        return "redirect:displayVendingPage?itemNumber=" + itemNumber;

    }

    @RequestMapping(value = "/displaySelectedItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) {

        //select item number for view
        String itemNumber = request.getParameter("itemNumber");

        return "redirect:displayVendingPage?itemNumber=" + itemNumber;

    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request)
            throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInInventoryException {

        String message;
        String itemNumberString = "";
        String change = "";

        try {
            itemNumberString = request.getParameter("itemNumber");
            int itemNumber = Integer.parseInt(itemNumberString);

            String balanceString = request.getParameter("balance");
            BigDecimal balance = new BigDecimal(balanceString);

            change = service.vendItem(itemNumber, balance);

            message = "Thank you!";

            service.clearBalance();

        } catch (NumberFormatException e) {
            message = "You must select an item to purchase!";
        } catch (VendingMachineInsufficientFundsException e) {
            message = e.getMessage();
        } catch (VendingMachineNoItemInInventoryException e) {
            message = "SOLD OUT!";
        }

        return "redirect:displayVendingPage?itemNumber="
                + itemNumberString + "&change=" + change + "&itemMessage=" + message;
    }

    @RequestMapping(value = "/returnMoney", method = RequestMethod.GET)
    public String changeReturn(HttpServletRequest request) {

        BigDecimal balance = service.getBalance();

        String change = service.makeChange(balance);

        service.clearBalance();

        return "redirect:displayVendingPage?change=" + change;
    }
}
