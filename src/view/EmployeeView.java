package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeView extends Layout{

    private User user;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotels;
    private JScrollPane scrl_hotels;
    private JTable tbl_hotels;
    private JPanel pnl_hotel_filter;
    private JTextField fld_hotel_name;
    private JButton btn_hotel_search;
    private JButton btn_clear;
    private JButton btn_add_new_hotel;
    private JLabel lbl_pension;
    private JPanel pnl_rooms;
    private JPanel pnl_reservation;
    private JScrollPane scrl_rooms;
    private JTable tbl_room;
    private JPanel pnl_room_filter;
    private JTextField fld_room_hotelname;
    private JTextField fld_room_checkin;
    private JTextField fld_room_checkout;
    private JButton btn_room_search;
    private JTextField fld_city;
    private JPanel pnl_room;
    private JButton btn_pension;
    private JButton btn_add_season;
    private JComboBox cmb_room_hotelname;
    private JTextField fld_room_city;
    private JButton btn_add_room;
    private JButton btn_clear1;
    public JTextField tfld_child_number;
    public JTextField tfld_adult_number;
    private JTable tbl_reservation;
    private JScrollPane scrl_res;
    private JPanel pnl_res;
    private JButton btn_load_hotels;
    private JComboBox cmb_room_address;
    private JPopupMenu popup_hotels = new JPopupMenu();
    private JPopupMenu popup_rooms = new JPopupMenu();
    private JPopupMenu popup_reservations = new JPopupMenu();
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private HotelManager hotelManager;
    private Hotel hotel;

    private Room room;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomSaveView roomSaveView;
    private ReservationManager reservationManager;
    public EmployeeView(){
    }
    public EmployeeView(User user){
        this.reservationManager = new ReservationManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.user = user;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(1500,500, "Tourism Agency Management System - Personal Panel");
        this.lbl_welcome.setText("Welcome " + this.user.getUsername());
        loadUserButtons();
        loadHotelTable(null);
        loadRoomTable(null);
        loadReservationTable(null);
        loadTablesPopupMenu();



        btn_load_hotels.addActionListener(e -> {
            this.cmb_room_hotelname.setModel(new DefaultComboBoxModel<>());
            for (Hotel hotels : this.hotelManager.findAll()){
                this.cmb_room_hotelname.addItem(hotels.getName());
            }
            this.cmb_room_hotelname.setSelectedItem(null);
        });


    }



    private void loadUserButtons(){
        btn_logout.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
        });

        btn_add_new_hotel.addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView(new Hotel());
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });

        this.btn_hotel_search.addActionListener(e -> {
            ArrayList<Hotel> filteredUsers = this.hotelManager.filter(
                    this.fld_city.getText().toUpperCase()
            );
            loadHotelTable(filteredUsers);
        });

        this.btn_clear.addActionListener(e -> {
            loadHotelTable(null);
//            this.fld_hotel_name.setText(null);
            this.fld_city.setText(null);
        });
        this.btn_pension.addActionListener(e -> {
            PensionView pensionView = new PensionView(new Pension(), new Hotel());
        });
        btn_add_season.addActionListener(e -> {
            SeasonView seasonView = new SeasonView(new Season(),new Hotel());
        });

        btn_room_search.addActionListener(e -> {
            int selectedHotelIndex = this.cmb_room_hotelname.getSelectedIndex();
            if((this.cmb_room_hotelname.getSelectedItem() != null) && (this.fld_room_city != null) && (this.fld_room_checkin != null) && (this.fld_room_checkout != null)){
                ArrayList<Room> filteredRooms = this.roomManager.filterNew(
                        this.hotelManager.findAll().get(selectedHotelIndex).getName(),
                        this.fld_room_city.getText().toUpperCase(),
                        Helper.convertStringToSqlDate(this.fld_room_checkin.getText()),
                        Helper.convertStringToSqlDate(this.fld_room_checkout.getText())
                );
                loadRoomTable(filteredRooms);
            }else if((this.fld_room_city == null) && (this.fld_room_checkin == null) && (this.fld_room_checkout == null)){
                ArrayList<Room> filteredRooms0 = this.roomManager.filterNew(
                        this.hotelManager.findAll().get(selectedHotelIndex).getName(),
                        null,
                        null,
                        null
                );
                loadRoomTable(filteredRooms0);
            } else if(this.cmb_room_hotelname.getSelectedItem() == null){
                ArrayList<Room> filteredRooms1 = this.roomManager.filterNew(null, this.fld_room_city.getText().toUpperCase(),
                        Helper.convertStringToSqlDate(this.fld_room_checkin.getText()),Helper.convertStringToSqlDate(this.fld_room_checkout.getText()));
                loadRoomTable(filteredRooms1);
            }else if((this.cmb_room_hotelname.getSelectedItem() == null) && (this.fld_room_checkin == null) && (this.fld_room_checkout == null)){
                ArrayList<Room> filteredRooms2 = this.roomManager.filterNew(null, this.fld_room_city.getText().toUpperCase(),
                        null,null);
                loadRoomTable(filteredRooms2);
            } else if ((this.cmb_room_hotelname == null) && (this.fld_room_city == null)) {
                ArrayList<Room> filteredRooms3 = this.roomManager.filterNew(null, null,
                        Helper.convertStringToSqlDate(this.fld_room_checkin.getText()),Helper.convertStringToSqlDate(this.fld_room_checkout.getText()));
                loadRoomTable(filteredRooms3);
            }


        });
        btn_add_room.addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(new Room());
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        btn_clear1.addActionListener(e -> {
            loadRoomTable(null);
            this.fld_room_city.setText(null);
            this.fld_room_checkin.setText(null);
            this.fld_room_checkout.setText(null);
        });
    }


    private void loadTablesPopupMenu() {
        this.tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_room.rowAtPoint(e.getPoint());
                tbl_room.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        this.popup_rooms.add("Add Reservation").addActionListener(e -> {
            int selectId = getTableSelectedRow(tbl_room, 0);
            if (!fld_room_checkin.getText().trim().isEmpty() && !fld_room_checkout.getText().trim().isEmpty() && !tfld_adult_number.getText().trim().isEmpty()) {
                tfld_child_number.setText(String.valueOf(0));
                ReservationView reservationView = new ReservationView(new Reservation(), this.roomManager.getById(selectId), this.fld_room_checkin, this.fld_room_checkout, this.tfld_adult_number, this.tfld_child_number);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable(null);
                        loadRoomTable(null);
                    }
                });
            } else {
                Helper.showMsg("Please fill in all required fields before adding a reservation.");
            }
        });
        this.tbl_reservation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_reservation.rowAtPoint(e.getPoint());
                tbl_reservation.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        this.popup_reservations.add("Delete").addActionListener(e -> {

            try{
                int selectedReservationId = this.getTableSelectedRow(this.tbl_reservation,0);
                this.room = this.roomManager.getById(this.reservationManager.getById(selectedReservationId).getRoomId());
                int stock = room.getStock();
                if(Helper.confirm("sure")){
                    if(this.reservationManager.delete(selectedReservationId)){
                        Helper.showMsg("Reservation deleted.");
                        loadReservationTable(null);
                        stock += 1;
                        if(this.roomManager.updateRoomStock(stock, this.room)){
                            loadRoomTable(null);
                        }
                    }
                }
            }catch (Exception exp){
                Helper.showMsg("Reservation has been deleted before.");
                System.out.println(exp.getMessage());
            }

        });
        this.popup_reservations.add("Update").addActionListener(e -> {
            try{
                int selectedReservationId = this.getTableSelectedRow(this.tbl_reservation,0);
                ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectedReservationId),this.roomManager.getById(selectedReservationId), this.fld_room_checkin, this.fld_room_checkout,this.tfld_adult_number,this.tfld_child_number);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable(null);
                    }
                });
            }catch (Exception exp){
                exp.printStackTrace();
            }


        });

        this.tbl_reservation.setComponentPopupMenu(this.popup_reservations);

        this.popup_rooms.add("Delete").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")) {
                if (this.roomManager.delete(selectId)) {

                    Helper.showMsg("done");
                    loadRoomTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.popup_rooms.add("Update").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(),0).toString());
            RoomSaveView roomSaveView = new RoomSaveView(this.roomManager.getById(selectId));
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        this.tbl_room.setComponentPopupMenu(this.popup_rooms);



        this.tbl_hotels.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotels.rowAtPoint(e.getPoint());
                tbl_hotels.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        this.popup_hotels.add("Delete").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")) {
                if (this.hotelManager.delete(selectId)) {

                    Helper.showMsg("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.popup_hotels.add("Update").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(),0).toString());
            HotelSaveView hotelSaveView = new HotelSaveView(this.hotelManager.getById(selectId));
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });

        this.tbl_hotels.setComponentPopupMenu(this.popup_hotels);
    }

    public void loadReservationTable(ArrayList<Reservation> reservationList){
        Object[] column = {"Reservation ID", "Hotel", "CheckIn", "CheckOut", "TotalPrice", "Guest Count", "Guest Name",
                "Guest CitizenID", "GuestMail", "GuestPhone", "isActive"};


        if(reservationList == null){
            reservationList = this.reservationManager.findAll();
        }
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_reservation.getModel();
        clearModel.setRowCount(0);
        this.tmdl_reservation.setColumnIdentifiers(column);

        for (Reservation reservation : reservationList){
            Object[] obj = {reservation.getId(), this.hotelManager.getById(this.roomManager.getById(reservation.getRoomId()).getHotelId()).getName(), reservation.getCheckInDate(),reservation.getCheckOutDate(),
                    reservation.getTotalPrice(), reservation.getGuestCount(), reservation.getGuestName() ,reservation.getGuestCitizenId() ,reservation.getGuestMail() ,
                    reservation.getGuestPhone(), this.reservationManager.getById(reservation.getId()).getId()==reservation.getId() ? "Active" : "Deleted"  };
            this.tmdl_reservation.addRow(obj);
        }
        tbl_reservation.setModel(tmdl_reservation);
        tbl_reservation.getTableHeader().setReorderingAllowed(false);
        tbl_reservation.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_reservation.setEnabled(false);

    }

    private void loadHotelTable(ArrayList<Hotel> hotels){
        Object[] column = {"ID", "Name", "Address", "Mail", "Phone", "Star",
                "Car Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa","Room service"};
        if(hotels == null){
            hotels = this.hotelManager.findAll();
        }
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_hotels.getModel();
        clearModel.setRowCount(0);
        this.tmdl_hotel.setColumnIdentifiers(column);
        for (Hotel hotel : hotels){
            Object[] obj = {hotel.getId(), hotel.getName(), hotel.getAddress(),hotel.getMail(),
            hotel.getPhone(), hotel.getStar(), Helper.handleBoolean(hotel.isCarPark()) ,Helper.handleBoolean(hotel.isWifi()) ,Helper.handleBoolean(hotel.isPool()) ,
            Helper.handleBoolean(hotel.isFitness()) ,Helper.handleBoolean(hotel.isConcierge()) ,Helper.handleBoolean(hotel.isSpa()) ,Helper.handleBoolean(hotel.isRoomService()) };
            this.tmdl_hotel.addRow(obj);
        }
        tbl_hotels.setModel(tmdl_hotel);
        tbl_hotels.getTableHeader().setReorderingAllowed(false);
        tbl_hotels.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_hotels.setEnabled(false);

    }

    private void loadRoomTable(ArrayList<Room> rooms){
        Object[] column = {"ID", "Hotel", "Address", "Pension", "Type", "Stock", "Season",
                "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar","Game Console","Cash Box","Projection"};
        if(rooms == null){
            rooms = this.roomManager.findAll();
        }
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_room.getModel();
        clearModel.setRowCount(0);
        this.tmdl_room.setColumnIdentifiers(column);
        for (Room room : rooms){
            Object[] obj = {room.getId(), this.hotelManager.getById(room.getHotelId()).getName(),
                    this.hotelManager.getById(room.getHotelId()).getAddress(),
                    this.pensionManager.getById(room.getPensionId()).getPensionType(),
                    room.getType(), room.getStock(), this.seasonManager.findSeasonByID(room.getSeasonId()).toStringDates()  ,room.getAdultPrice(), room.getChildPrice(),
                    room.getBedCapacity(), room.getSquareMeter(), Helper.handleBoolean(room.isTelevision()), Helper.handleBoolean(room.isMinibar()) , Helper.handleBoolean(room.isGameConsole()) ,
                    Helper.handleBoolean(room.isCashBox()) , Helper.handleBoolean(room.isProjection()) };
            this.tmdl_room.addRow(obj);
        }
        tbl_room.setModel(tmdl_room);
        tbl_room.getTableHeader().setReorderingAllowed(false);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_room.setEnabled(false);

    }
}
