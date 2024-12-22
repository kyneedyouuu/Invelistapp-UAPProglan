package com.invelistwarehouseinventory.ApplicationGUI;

import com.invelistwarehouseinventory.Exception.NumericDocumentFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class InvelistApp extends JFrame {
    private JTextField itemIdField, itemNameField, itemCategoryField, itemPriceField, itemQuantityField;
    private JLabel itemImageLabel;
    private DefaultTableModel itemTableModel;
    private String itemImagePath;
    private JTable itemTable;
    private boolean isAdmin;

    public InvelistApp(boolean isAdmin) {
        this.isAdmin = isAdmin;
        setTitle("Invelist Gudang Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font customFont = new Font("Poppins", Font.TRUETYPE_FONT, 14);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(customFont);

        // Panel untuk mengelola item
        JPanel itemPanel = new JPanel(new BorderLayout());
        JPanel itemInputPanel = new JPanel(new GridBagLayout());
        itemInputPanel.setBackground(new Color(255, 228, 196));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        itemInputPanel.add(createLabel("ID Item:", customFont), gbc);
        itemIdField = new JTextField();
        ((AbstractDocument) itemIdField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        gbc.gridx = 1;
        itemInputPanel.add(itemIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        itemInputPanel.add(createLabel("Nama Item:", customFont), gbc);
        itemNameField = new JTextField();
        gbc.gridx = 1;
        itemInputPanel.add(itemNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        itemInputPanel.add(createLabel("Kategori:", customFont), gbc);
        itemCategoryField = new JTextField();
        gbc.gridx = 1;
        itemInputPanel.add(itemCategoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        itemInputPanel.add(createLabel("Harga:", customFont), gbc);
        itemPriceField = new JTextField();
        ((AbstractDocument) itemPriceField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        gbc.gridx = 1;
        itemInputPanel.add(itemPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        itemInputPanel.add(createLabel("Jumlah:", customFont), gbc);
        itemQuantityField = new JTextField();
        ((AbstractDocument) itemQuantityField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        gbc.gridx = 1;
        itemInputPanel.add(itemQuantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        itemInputPanel.add(createLabel("Gambar:", customFont), gbc);
        itemImageLabel = new JLabel();
        JButton uploadImageButton = new JButton("Upload Gambar");
        uploadImageButton.setFont(customFont);
        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    itemImagePath = selectedFile.getAbsolutePath();
                    itemImageLabel.setIcon(new ImageIcon(new ImageIcon(itemImagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                }
            }
        });
        gbc.gridx = 1;
        itemInputPanel.add(uploadImageButton, gbc);
        gbc.gridx = 2;
        itemInputPanel.add(itemImageLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        JButton addItemButton = new JButton("Tambah Item");
        addItemButton.setFont(customFont);
        addItemButton.setBackground(new Color(144, 238, 144)); // Warna hijau muda
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validateItemInput()) {
                        String formattedPrice = formatPrice(itemPriceField.getText());
                        itemTableModel.addRow(new Object[]{
                                itemIdField.getText(),
                                itemNameField.getText(),
                                itemCategoryField.getText(),
                                formattedPrice,
                                itemQuantityField.getText(),
                                itemImagePath != null ? new ImageIcon(new ImageIcon(itemImagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)) : null
                        });
                        clearItemFields();
                        JOptionPane.showMessageDialog(null, "Item berhasil ditambahkan!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        itemInputPanel.add(addItemButton, gbc);

        gbc.gridx = 1;
        JButton updateItemButton = new JButton("Update Item");
        updateItemButton.setFont(customFont);
        updateItemButton.setBackground(new Color(173, 216, 230)); // Warna biru muda
        updateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = itemTable.getSelectedRow();
                    if (selectedRow != -1 && validateItemInput()) {
                        String formattedPrice = formatPrice(itemPriceField.getText());
                        itemTableModel.setValueAt(itemIdField.getText(), selectedRow, 0);
                        itemTableModel.setValueAt(itemNameField.getText(), selectedRow, 1);
                        itemTableModel.setValueAt(itemCategoryField.getText(), selectedRow, 2);
                        itemTableModel.setValueAt(formattedPrice, selectedRow, 3);
                        itemTableModel.setValueAt(itemQuantityField.getText(), selectedRow, 4);
                        if (itemImagePath != null) {
                            itemTableModel.setValueAt(new ImageIcon(new ImageIcon(itemImagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)), selectedRow, 5);
                        }
                        clearItemFields();
                        JOptionPane.showMessageDialog(null, "Item berhasil diupdate!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pilih item yang akan diupdate dan pastikan semua field diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        itemInputPanel.add(updateItemButton, gbc);

        gbc.gridx = 2;
        JButton deleteItemButton = new JButton("Hapus Item");
        deleteItemButton.setFont(customFont);
        deleteItemButton.setBackground(new Color(255, 99, 71)); // Warna merah
        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = itemTable.getSelectedRow();
                    if (selectedRow != -1) {
                        itemTableModel.removeRow(selectedRow);
                        clearItemFields();
                        JOptionPane.showMessageDialog(null, "Item berhasil dihapus!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pilih item yang akan dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        itemInputPanel.add(deleteItemButton, gbc);

        itemTableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Kategori", "Harga", "Jumlah", "Gambar"}, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };
        itemTable = new JTable(itemTableModel);
        itemTable.setFont(customFont);
        itemTable.setRowHeight(100);
        itemTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                itemIdField.setText(itemTableModel.getValueAt(selectedRow, 0).toString());
                itemNameField.setText(itemTableModel.getValueAt(selectedRow, 1).toString());
                itemCategoryField.setText(itemTableModel.getValueAt(selectedRow, 2).toString());
                itemPriceField.setText(itemTableModel.getValueAt(selectedRow, 3).toString().replace("Rp ", "").replace(",", ""));
                itemQuantityField.setText(itemTableModel.getValueAt(selectedRow, 4).toString());
                ImageIcon imageIcon = (ImageIcon) itemTableModel.getValueAt(selectedRow, 5);
                itemImageLabel.setIcon(imageIcon);
                itemImagePath = imageIcon != null ? imageIcon.getDescription() : null;
            }
        });
        JScrollPane itemScrollPane = new JScrollPane(itemTable);

        itemPanel.add(itemInputPanel, BorderLayout.NORTH);
        itemPanel.add(itemScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Data Item", itemPanel);

        add(tabbedPane);

        addInitialData();

        // Disable buttons for non-admin users
        if (!isAdmin) {
            addItemButton.setEnabled(false);
            updateItemButton.setEnabled(false);
            deleteItemButton.setEnabled(false);
            uploadImageButton.setEnabled(false);
        }
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private void addInitialData() {
        itemTableModel.addRow(new Object[]{
                "001",
                "Asus ROG Strix G30S",
                "Devices",
                formatPrice("999999"),
                "2",
                new ImageIcon(new ImageIcon("/image/laptop.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))
        });
        itemTableModel.addRow(new Object[]{
                "002",
                "Mechanical Keyboard with Red Switch Extra Keycaps",
                "Peripheral",
                formatPrice("2000000"),
                "3",
                new ImageIcon(new ImageIcon("image/keyboard.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))
        });
        itemTableModel.addRow(new Object[]{
                "003",
                "Gaming Mouse with extra AIM++",
                "Peripheral",
                formatPrice("10000000"),
                "5",
                new ImageIcon(new ImageIcon("image/mouse.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))
        });
    }

    private boolean validateItemInput() {
        return !itemIdField.getText().isEmpty() && !itemNameField.getText().isEmpty() &&
                !itemCategoryField.getText().isEmpty() && !itemPriceField.getText().isEmpty() &&
                !itemQuantityField.getText().isEmpty();
    }

    private void clearItemFields() {
        itemIdField.setText("");
        itemNameField.setText("");
        itemCategoryField.setText("");
        itemPriceField.setText("");
        itemQuantityField.setText("");
        itemImageLabel.setIcon(null);
        itemImagePath = null;
    }

    private String formatPrice(String price) {
        int priceValue = Integer.parseInt(price);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return currencyFormat.format(priceValue).replace("Rp", "Rp ");
    }
}