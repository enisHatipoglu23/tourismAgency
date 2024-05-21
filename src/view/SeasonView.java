package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SeasonView extends Layout {


    private JPanel container;
    private JLabel lbl_season;
    private JPanel pnl_season;
    private JComboBox cmb_seas_hotel;
    private JLabel lbl_seas_htl;
    private JTextField tfld_start;
    private JTextField tfld_finish;
    private JLabel fld_start;
    private JPanel pnl_strt;
    private JLabel jfld_finish;
    private JButton btn_save;
    private HotelManager hotelManager;
    private Season season;
    private Hotel hotel;
    private SeasonManager seasonManager;

    // add season panel features applied in this class
    public SeasonView(Season season, Hotel hotel) {
        this.season = season;
        this.hotel = hotel;
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.add(this.container);
        this.guiInitilaze(500, 500, "Add Season");

        this.cmb_seas_hotel.setModel(new DefaultComboBoxModel<>());

        for (Hotel hotels : this.hotelManager.findAll()) {
            this.cmb_seas_hotel.addItem(hotels.getName());
        }
        this.cmb_seas_hotel.setSelectedItem(null);

        btn_save.addActionListener(e -> {
            int selectedHotelIndex = cmb_seas_hotel.getSelectedIndex();

            if (selectedHotelIndex == -1) {
                Helper.showMsg("Lütfen bir otel seçin.");
            }
            int selectedHotelId = hotelManager.findAll().get(selectedHotelIndex).getId();
            LocalDate startDate = LocalDate.parse(this.tfld_start.getText());
            LocalDate finishDate = LocalDate.parse(this.tfld_finish.getText());


            this.season.setHotelId(selectedHotelId);
            this.season.setStartDate(startDate);
            this.season.setFinishDate(finishDate);

            if (this.seasonManager.save(this.season)) {
                Helper.showMsg("Season added successfully. ");
                // İşlem başarılı oldu, ekranı kapat
                dispose();
            } else {
                Helper.showMsg("Error. ");
            }
        });

    }
}

