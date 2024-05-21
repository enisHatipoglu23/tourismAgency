package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class RoomSaveView extends Layout{
    private JPanel container;
    private JLabel fld_room;
    private JLabel fld_hotel_room;
    private JComboBox cmb_hotel_select;
    private JComboBox cmb_pension_select;
    private JComboBox cmb_season_select;
    private JComboBox cmb_select_room_type;
    private JTextField tfld_stock;
    private JTextField tfld_adult_price;
    private JTextField tfld_child_price;
    private JRadioButton rdo_minibar;
    private JRadioButton rdo_tv;
    private JRadioButton rdo_gameconsole;
    private JRadioButton rdo_cashbox;
    private JRadioButton rdo_projection;
    private JButton btn_save;
    private JLabel fld_pension;
    private JLabel fld_season;
    private JLabel fld_room_type;
    private JLabel fld_stock;
    private JLabel fld_adult_price;
    private JLabel fld_child_price;
    private JTextField tfd_bed_capacity;
    private JLabel fld_bed;
    private JTextField tfld_square;
    private JLabel fld_square;
    private Room room;
    private Hotel hotel;
    private Season season;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;

    // add/update room panel features applied in this class
    public RoomSaveView(Room room){
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.room = room;
        this.add(container);
        this.guiInitilaze(800,500, "Add Room");

        this.loadComboBoxFields();

        cmb_hotel_select.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateSeasonsAndPensions();
            }
        });
        if(this.room.getId() == 0){
            this.fld_room.setText("Add Room");
        }else{
            this.fld_room.setText("Edit Room");
            this.cmb_hotel_select.setSelectedItem(this.hotelManager.getById(this.room.getHotelId()).getName());
            this.cmb_season_select.setSelectedItem(this.seasonManager.findSeasonByID(this.room.getSeasonId()).toStringDates());
            this.cmb_pension_select.setSelectedItem(this.pensionManager.getById(this.room.getPensionId()).getPensionType());
            this.cmb_select_room_type.setSelectedItem(this.room.getType());
            this.tfld_stock.setText(String.valueOf(this.room.getStock()));
            this.tfld_adult_price.setText(String.valueOf(this.room.getAdultPrice()));
            this.tfld_child_price.setText(String.valueOf(this.room.getChildPrice()));
            this.tfd_bed_capacity.setText(String.valueOf(this.room.getBedCapacity()));
            this.tfld_square.setText(String.valueOf(this.room.getSquareMeter()));
            this.rdo_tv.setSelected(this.room.isTelevision());
            this.rdo_gameconsole.setSelected(this.room.isGameConsole());
            this.rdo_projection.setSelected(this.room.isProjection());
            this.rdo_minibar.setSelected(this.room.isMinibar());
            this.rdo_cashbox.setSelected(this.room.isCashBox());
        }

        btn_save.addActionListener(e -> {
            JTextField[] textfCheckList = {tfld_stock, tfld_adult_price, tfld_child_price};

            if (Helper.isFieldListEmpty(textfCheckList)) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;

                Object hotelSelection = cmb_hotel_select.getSelectedItem();
                if (hotelSelection == null) {
                    Helper.showMsg("Please select a hotel first.");
                    return;
                }
                int selectedHotelIndex = cmb_hotel_select.getSelectedIndex();
                int selectedHotelId = this.hotelManager.findAll().get(selectedHotelIndex).getId();

                Object seasonSelection = cmb_season_select.getSelectedItem();
                if (seasonSelection == null) {
                    Helper.showMsg("Please select a season.");
                    return;
                }
                int selectedSeasonIndex = cmb_season_select.getSelectedIndex();
                int selectedSeasonId = this.seasonManager.findAll().get(selectedSeasonIndex).getId();

                Object pensionSelection = cmb_pension_select.getSelectedItem();
                if (pensionSelection == null) {
                    Helper.showMsg("Please select a pension type.");
                    return;
                }
                int selectedPensionIndex = cmb_pension_select.getSelectedIndex();
                int selectedPensionId = pensionManager.findPensionByHotelId(selectedHotelId).get(selectedPensionIndex).getId();

                this.room.setHotelId(selectedHotelId);
                this.room.setSeasonId(selectedSeasonId);
                this.room.setPensionId(selectedPensionId);
                this.room.setType(cmb_select_room_type.getSelectedItem().toString());
                this.room.setStock(Integer.parseInt(tfld_stock.getText()));
                this.room.setAdultPrice(Double.parseDouble(tfld_adult_price.getText()));
                this.room.setChildPrice(Double.parseDouble(tfld_child_price.getText()));
                this.room.setBedCapacity(Integer.parseInt(tfd_bed_capacity.getText()));
                this.room.setSquareMeter(Integer.parseInt(tfld_square.getText()));
                this.room.setTelevision(rdo_tv.isSelected());
                this.room.setGameConsole(rdo_gameconsole.isSelected());
                this.room.setProjection(rdo_projection.isSelected());
                this.room.setMinibar(rdo_minibar.isSelected());
                this.room.setCashBox(rdo_cashbox.isSelected());

                if (this.room.getId() == 0) {
                    result = roomManager.save(this.room);
                } else {
                    result = roomManager.update(this.room);
                }

                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });


    }

    private void loadComboBoxFields(){
        this.cmb_hotel_select.setModel(new DefaultComboBoxModel<>());

        for (Hotel hotel : this.hotelManager.findAll()){
            this.cmb_hotel_select.addItem(hotel.getName());
        }

        this.cmb_hotel_select.setSelectedItem(null);

        this.cmb_pension_select.setModel(new DefaultComboBoxModel<>());
        this.cmb_season_select.setModel(new DefaultComboBoxModel<>());

        updateSeasonsAndPensions();

        String[] roomTypes = {"Single Room", "Double Room", "Junior Suite Room", "Suite Room"};
        for(String room : roomTypes){
            this.cmb_select_room_type.addItem(room);
        }
        this.cmb_select_room_type.setSelectedItem(null);
    }

    private void updateSeasonsAndPensions() {
        int selectedHotelIndex = cmb_hotel_select.getSelectedIndex();
        if (selectedHotelIndex >= 0) {
            int selectedHotelId = hotelManager.findAll().get(selectedHotelIndex).getId();

            cmb_pension_select.setModel(new DefaultComboBoxModel<>());
            this.pensionManager.findPensionByHotelId(selectedHotelId).forEach(pension -> {
                cmb_pension_select.addItem(pension.getPensionType());
            });


            cmb_season_select.setModel(new DefaultComboBoxModel<>());
            seasonManager.findSeasonByHotelID(selectedHotelId).forEach(season -> {
                cmb_season_select.addItem(season.getStartDate() + " to " + season.getFinishDate());
            });
        }


    }
}
