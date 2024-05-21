package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelSaveView extends Layout{
    private JPanel container;
    private JLabel lbl_add_hotel;
    private JPanel pnl_hotel_add;
    private JTextField fld_hotelname;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JTextField fld_address;
    private JRadioButton rdo_carpark;
    private JRadioButton rdo_wifi;
    private JRadioButton rdo_pool;
    private JRadioButton rdo_fitness;
    private JRadioButton rdo_concierge;
    private JRadioButton rdo_spa;
    private JRadioButton rdo_roomservice;
    private JButton btn_save;
    private JComboBox<String> cmb_star;
    private JLabel lbl_hotelname;
    private JLabel lbl_mail;
    private JLabel lbl_phone;
    private JLabel lbl_address;
    private JLabel fld_star;
    private HotelManager hotelManager;
    private Hotel hotel;

    // add hotel panel features applied in this class
    public HotelSaveView(Hotel hotel){
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(750,500,"Add/Update");
        this.cmb_star.setModel(new DefaultComboBoxModel<>());
        String[] stars = {"1","2","3","4","5"};
        for (int i = 0; i<stars.length; i++){
            cmb_star.addItem(stars[i]);
        }
        if(this.hotel.getId() == 0) {
            this.lbl_add_hotel.setText("New Hotel Registration");
        }else {
            this.lbl_add_hotel.setText("Edit User");
            this.fld_hotelname.setText(hotel.getName());
            this.fld_mail.setText(hotel.getMail());
            this.fld_address.setText(hotel.getAddress());
            this.fld_phone.setText(hotel.getPhone());
            this.cmb_star.setSelectedItem(hotel.getStar());
            this.rdo_wifi.setSelected(hotel.isWifi());
            this.rdo_spa.setSelected(hotel.isSpa());
            this.rdo_carpark.setSelected(hotel.isCarPark());
            this.rdo_concierge.setSelected(hotel.isConcierge());
            this.rdo_fitness.setSelected(hotel.isFitness());
            this.rdo_pool.setSelected(hotel.isPool());
            this.rdo_roomservice.setSelected(hotel.isRoomService());
        }





        btn_save.addActionListener(e -> {
            JTextField[] textfCheckList = {fld_hotelname,fld_address,fld_phone,fld_mail};

            if(Helper.isFieldListEmpty(textfCheckList)){
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                this.hotel.setName(this.fld_hotelname.getText());
                this.hotel.setAddress(this.fld_address.getText());
                this.hotel.setPhone(this.fld_phone.getText());
                this.hotel.setMail(this.fld_mail.getText());
                this.hotel.setStar(this.cmb_star.getSelectedItem().toString());
                this.hotel.setSpa(this.rdo_spa.isSelected());
                this.hotel.setConcierge(this.rdo_concierge.isSelected());
                this.hotel.setWifi(this.rdo_wifi.isSelected());
                this.hotel.setPool(this.rdo_pool.isSelected());
                this.hotel.setRoomService(this.rdo_roomservice.isSelected());
                this.hotel.setFitness(this.rdo_fitness.isSelected());
                this.hotel.setCarPark(this.rdo_carpark.isSelected());

                if(this.hotel.getId() == 0){
                    result = this.hotelManager.save(this.hotel);
                }else{
                    result = this.hotelManager.update(this.hotel);
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
