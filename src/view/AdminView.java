package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminView extends Layout{

    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_users;
    private JTable tbl_user;
    private JTextField fld_f_username;
    private JComboBox<Role> cmb_f_user_role;
    private JButton btn_user_filter;
    private JButton btn_user_clear;
    private JButton btn_user_new;
    private JPanel pnl_user_filter;
    private JLabel lbl_f_username;
    private JLabel lbl_f_user_role;

    private JPopupMenu popup_user = new JPopupMenu();
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private User user;

    public AdminView(User user){
        this.user = user;
        if(this.user == null){
            dispose();
        }
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(1000,500, "Tourism Agency Management System - Admin Panel");
        this.lbl_welcome.setText("Welcome " + this.user.getUsername());
        logoutButton();
        loadUserTable(null);
        loadUserPopupMenu();
        loadUserButtons();

        btn_user_filter.addActionListener(e -> {
            loadUserTable(null);
        });

        this.cmb_f_user_role.setModel(new DefaultComboBoxModel<>(Role.values()));
        this.cmb_f_user_role.setSelectedItem(null);
    }

    private void logoutButton(){
        btn_logout.addActionListener(e ->  {
            dispose();
            LoginView loginView = new LoginView();
        });
    }

    private void loadUserTable(ArrayList<User> users){
        Object[] column = {"ID", "Username", "Role"};
        if(users == null){
            users = this.userManager.findAll();
        }
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_user.getModel();
        clearModel.setRowCount(0);
        this.tmdl_user.setColumnIdentifiers(column);
        for (User user1 : users){
            Object[] obj = {user1.getUserId(), user1.getUsername(), user1.getRole().toString()};
            this.tmdl_user.addRow(obj);
        }
        tbl_user.setModel(tmdl_user);
        tbl_user.getTableHeader().setReorderingAllowed(false);
        tbl_user.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_user.setEnabled(false);

    }

    private void loadUserPopupMenu(){
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });

        this.popup_user.add("Sil").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            if(Helper.confirm("sure")){
                if(this.userManager.delete(selectId)){

                    Helper.showMsg("done");
                    loadUserTable(null);
                }else{
                    Helper.showMsg("error");
                }
            }

        });
        this.popup_user.add("Güncelle").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            EditView editView = new EditView(this.userManager.getById(selectId));
            editView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        this.tbl_user.setComponentPopupMenu(this.popup_user);
    }
    private void loadUserButtons(){
        this.btn_user_new.addActionListener(e -> {
            EditView editView = new EditView(new User());
            editView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.btn_user_filter.addActionListener(e -> {
            ArrayList<User> filteredUsers = this.userManager.filter(
                    this.fld_f_username.getText(),
                    (Role) this.cmb_f_user_role.getSelectedItem()
            );
            loadUserTable(filteredUsers);
        });

        this.btn_user_clear.addActionListener(e -> {
            loadUserTable(null);
            this.fld_f_username.setText(null);
            this.cmb_f_user_role.setSelectedItem(null);
        });
    }



}

