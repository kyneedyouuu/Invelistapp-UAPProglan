package com.invelistwarehouseinventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

public class InvelistAppTest {
    private com.invelistwarehouseinventory.ApplicationGUI.InvelistApp app;
    private DefaultTableModel model;

    @BeforeEach
    public void setUp() {
        app = new com.invelistwarehouseinventory.ApplicationGUI.InvelistApp(true);
        model = (DefaultTableModel) app.getItemTable().getModel();
    }

    @Test
    public void testAddItem() {
        app.getItemIdField().setText("4");
        app.getItemNameField().setText("Test Item");
        app.getItemCategoryField().setText("Test Category");
        app.getItemPriceField().setText("5000");
        app.getItemQuantityField().setText("10");
        app.setItemImagePath("path/to/test_image.jpg");

        app.getAddItemButton().doClick();

        assertEquals(4, model.getRowCount());
        assertEquals("4", model.getValueAt(3, 0));
        assertEquals("Test Item", model.getValueAt(3, 1));
        assertEquals("Test Category", model.getValueAt(3, 2));
        assertEquals("Rp 5.000", model.getValueAt(3, 3));
        assertEquals("10", model.getValueAt(3, 4));
        assertNotNull(model.getValueAt(3, 5));
    }

    @Test
    public void testUpdateItem() {
        app.getItemTable().setRowSelectionInterval(0, 0);
        app.getItemIdField().setText("1");
        app.getItemNameField().setText("Updated Item");
        app.getItemCategoryField().setText("Updated Category");
        app.getItemPriceField().setText("7000");
        app.getItemQuantityField().setText("20");
        app.setItemImagePath("path/to/updated_image.jpg");

        app.getUpdateItemButton().doClick();

        assertEquals("1", model.getValueAt(0, 0));
        assertEquals("Updated Item", model.getValueAt(0, 1));
        assertEquals("Updated Category", model.getValueAt(0, 2));
        assertEquals("Rp 7.000", model.getValueAt(0, 3));
        assertEquals("20", model.getValueAt(0, 4));
        assertNotNull(model.getValueAt(0, 5));
    }

    @Test
    public void testDeleteItem() {
        app.getItemTable().setRowSelectionInterval(0, 0);
        app.getDeleteItemButton().doClick();

        assertEquals(2, model.getRowCount());
    }
}