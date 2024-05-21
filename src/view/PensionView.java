package view;

import business.HotelManager;
import business.PensionManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout{
    private JPanel container;
    private JComboBox cmb_pns_hotel;
    private JButton btn_save_pens;
    private JComboBox cmb_pensions;
    private JLabel lbl_pens1;
    private JLabel lbl_pens2;

    private Pension pension;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private Hotel hotel;
    // add pension panel features applied in this class
    public PensionView(Pension pension,Hotel hotel){

        this.hotel = hotel;
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.pension = pension;
        this.add(container);
        this.guiInitilaze(300,500,"Add Pension");
        this.cmb_pns_hotel.setModel(new DefaultComboBoxModel<>());

        for (Hotel hotels : this.hotelManager.findAll()){
            this.cmb_pns_hotel.addItem(hotels.getName());
        }
        this.cmb_pns_hotel.setSelectedItem(null);
        this.cmb_pensions.setModel(new DefaultComboBoxModel<>());
        String[] types = {"Ultra Her şey Dahil", "Her şey Dahil", "Oda Kahvaltı", "Tam Pansiyon",
                "Yarım Pansiyon", "Sadece Yatak", "Alkol Hariç Full credit"};
        for (String type : types){
            cmb_pensions.addItem(type);
        }
        this.cmb_pensions.setSelectedItem(null);
        this.cmb_pns_hotel.setSelectedItem(null);

        btn_save_pens.addActionListener(e -> {
            int selectedHotelIndex = cmb_pns_hotel.getSelectedIndex();
            int selectedPensionIndex = cmb_pensions.getSelectedIndex();
            if (selectedHotelIndex == -1) {
                Helper.showMsg("Lütfen bir otel seçin.");
                return;
            }else if(selectedPensionIndex == -1){
                Helper.showMsg("Lütfen bir pansiyon tipi seçiniz.");
                return;
            }
            int selectedHotelId = hotelManager.findAll().get(selectedHotelIndex).getId();

            String selectedPensionType = cmb_pensions.getSelectedItem().toString();

            this.pension.setHotelId(selectedHotelId);
            this.pension.setPensionType(selectedPensionType);

            if (pensionManager.save(pension)) {
                Helper.showMsg("Pansiyon başarıyla kaydedildi.");
                // İşlem başarılı oldu, ekranı kapat
                dispose();
            } else {
                Helper.showMsg("Pansiyon kaydedilirken bir hata oluştu.");
            }
        });

    }



}
