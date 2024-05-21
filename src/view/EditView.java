package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditView extends Layout{
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_role;
    private JComboBox<Role> cmb_role;
    private JPasswordField psw_user;
    private JButton btn_save;
    private User user;
    private UserManager userManager;
    public EditView(User user){
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300,500, "Add / Update");
        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));

        if(this.user.getUserId() == 0){
            this.lbl_title.setText("New User Registration");
        }else{
            this.lbl_title.setText("Edit User");
            this.fld_username.setText(user.getUsername());
            this.cmb_role.getModel().setSelectedItem(this.user.getRole());
            this.psw_user.setText(this.user.getPassword());

        }
        btn_save.addActionListener(e -> {
            JTextField[] checkList = {fld_username,psw_user};
            if(Helper.isFieldListEmpty(checkList)){
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                this.user.setUsername(this.fld_username.getText());
                this.user.setPassword(this.psw_user.getText());
                this.user.setRole((Role) this.cmb_role.getSelectedItem());
                if(this.user.getUserId() == 0){
                    //kaydetme işlemi
                    result = this.userManager.save(this.user);
                }else{
                    //güncelleme işlemi
                    result = this.userManager.update(this.user);
                }

                if(result){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }
        });
    }
}
