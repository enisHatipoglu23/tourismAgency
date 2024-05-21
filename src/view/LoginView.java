package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;
import java.awt.*;

public class LoginView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JPanel w_bottom;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JTextField fld_password;
    private JButton btn_login;

    // login panel features applied in this class
    private UserManager userManager;
    public LoginView(){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400,400, "login");
        w_bottom.setBackground(Color.red);
        container.setBackground(Color.orange);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {fld_username, fld_password};
            if(Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else{
                User loginUser = userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser == null){
                    Helper.showMsg("notFound");
                }else{
                    // admin mi employee mi kontrolü burada yapılır.
                    Role role = loginUser.getRole();
                    switch (role){
                        case ADMIN -> openAdminView(loginUser);
                        case EMPLOYEE -> openEmployeeView(loginUser);
                        default -> throw new IllegalArgumentException();
                    }
                }
            }
        });
    }
    private void openAdminView(User user){
        AdminView adminView = new AdminView(user);
        adminView.setVisible(true);
        dispose();
    }

    private void openEmployeeView(User user){
        EmployeeView employeeView = new EmployeeView(user);
        employeeView.setVisible(true);
        dispose();
    }
}
