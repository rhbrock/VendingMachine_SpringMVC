/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class VendingMachineServiceTest {

    private VendingMachineService service;

    public VendingMachineServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("vendingMachineService", VendingMachineService.class);
        
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineService.
     */
    @Test
    public void testGetAllItems() {
        assertEquals(1, service.getAllItems().size());
    }

    /**
     * Test of selectItem method, of class VendingMachineService.
     */
    @Test
    public void testSelectItem() throws Exception {
        VendingMachine item = service.selectItem(1);

        assertEquals("Test", item.getItemName());
    }

    /**
     * Test of balance method, of class VendingMachineService.
     */
    @Test
    public void testBalance() {
        //no test
    }

    /**
     * Test of getBalance method, of class VendingMachineService.
     */
    @Test
    public void testGetBalance() {
        //no test
    }

    /**
     * Test of clearBalance method, of class VendingMachineService.
     */
    @Test
    public void testClearBalance() {
        //no test
    }

    /**
     * Test of vendItem method, of class VendingMachineService.
     */
    @Test
    public void testVendItem() throws Exception {
        //no test
    }

    /**
     * Test of makeChange method, of class VendingMachineService.
     */
    @Test
    public void testMakeChange() {
        //no test
    }

}
