/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.modal.VendingMachine;
import java.math.BigDecimal;
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
public class VendingMachineDaoTest {

    private VendingMachineDao dao;

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() {

//DAO is stocked with 9 items in InMemImpl constructor
        List<VendingMachine> itemList = dao.getAllItems();

        assertEquals(itemList.size(), 9);
    }

    /**
     * Test of selectItem method, of class VendingMachineDao.
     */
    @Test
    public void testSelectItem() {


        VendingMachine one = dao.selectItem(1);
        VendingMachine two = dao.selectItem(2);

        assertNotEquals(one.getItemName(), two.getItemName());
        
        assertEquals(one, dao.selectItem(1));
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditItem() {

        VendingMachine vm = dao.selectItem(1);

        int initial = vm.getItemQuantity();

        vm.setItemQuantity(9);

        dao.updateItem(vm);

        assertNotEquals(initial, vm.getItemQuantity());
    }
}
