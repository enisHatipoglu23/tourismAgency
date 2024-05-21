package view;

import business.HotelManager;
import business.PensionManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ReservationView extends Layout{
    private JPanel container;
    private JLabel lbl_hotelname_res;
    private JComboBox cmb_hotel_selec;
    private JComboBox cmb_room_selec;
    private JPanel pnl_hotel_selec_res;
    private JPanel pnl_rooms;
    private JTable tbl_reservation_info;
    private JLabel lbl_hotel_star;
    private JLabel lbl_hotel_mail;
    private JLabel lbl_pn;
    private JTextField fld_checkin;
    private JTextField fld_checkout;
    private JButton btn_addreservation_res;
    private JPanel pnl_pension;
    private JComboBox cmb_pension_selec;
    private JLabel lbl_guestinfo;
    private JLabel lbl_star_res;
    private JRadioButton rdo_carpark;
    private JRadioButton rdo_wifi;
    private JRadioButton rdo_pool;
    private JRadioButton rdo_fitness;
    private JRadioButton rdo_concierge;
    private JRadioButton rdo_spa;
    private JRadioButton rdo_room_service;
    private JLabel lbl_room_info;
    private JFormattedTextField fld_hotel_name_res;
    private JFormattedTextField fld_city_res;
    private JFormattedTextField fld_star_res;
    private JFormattedTextField fld_room_type_res;
    private JLabel lbl_pension_res;
    private JFormattedTextField fld_pension_type_res;
    private JTextField fld_checkout_res;
    private JTextField fld_checkin_res;
    private JFormattedTextField fld_total_amount_res;
    private JLabel lbl_totalamount_res;
    private JLabel lbl_room_type;
    private JFormattedTextField fld_bed_capacity_res;
    private JLabel lbl_bed_capacity_res;
    private JLabel lbl_square_meter;
    private JFormattedTextField fld_square_meter_res;
    private JRadioButton rdo_tv_res;
    private JRadioButton rdo_gameconsole_res;
    private JRadioButton rdo_projection_res;
    private JRadioButton rdo_cashbox_res;
    private JFormattedTextField fld_guest_count_res;
    private JFormattedTextField fld_guest_name_res;
    private JFormattedTextField fld_citizen_id_res;
    private JFormattedTextField fld_email_res;
    private JFormattedTextField fld_phone_res;
    private JLabel lbl_guestcount;
    private JLabel lbl_guest_name_res;
    private JLabel lbl_citizen_id_res;
    private JLabel lbl_email_res;
    private JLabel lbl_phone_res;

    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private Hotel hotel;
    private Room room;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private EmployeeView employeeView;

    // reservation panel features applied in this class
    public ReservationView(Reservation reservation, Room room, JTextField startDate, JTextField endDate, JTextField adult, JTextField child){

        this.reservationManager = new ReservationManager();
        this.reservation = reservation;
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.employeeView = new EmployeeView();
        this.room = room;
        this.add(this.container);
        this.guiInitilaze(1800,600,"Reservation Panel");

        if(this.reservation.getId() == 0){
            this.hotel = this.hotelManager.getById(this.room.getHotelId());
            this.fld_checkin_res.setText(startDate.getText());
            this.fld_checkout_res.setText(endDate.getText());
            int adultNumber = Integer.parseInt(adult.getText());
            int childNumber = Integer.parseInt(child.getText());
            this.fld_guest_count_res.setText(String.valueOf(adultNumber+childNumber));
            LocalDate startDate1 = LocalDate.parse(startDate.getText());
            LocalDate endDate1 = LocalDate.parse(endDate.getText());
            long daysBetween = ChronoUnit.DAYS.between(startDate1,endDate1);
            double totalAmount = ((this.room.getAdultPrice() * adultNumber) + (this.room.getChildPrice() * childNumber))*daysBetween;
            this.fld_total_amount_res.setText(String.valueOf((totalAmount)));

        }else{
            this.room = this.roomManager.getById(this.reservation.getRoomId());
            this.hotel = this.hotelManager.getById(this.room.getHotelId());
            fld_total_amount_res.setText(String.valueOf(this.reservation.getTotalPrice()));
            fld_guest_count_res.setText(String.valueOf(this.reservation.getGuestCount()));
            fld_guest_name_res.setText(this.reservation.getGuestName());
            fld_citizen_id_res.setText(this.reservation.getGuestCitizenId());
            fld_email_res.setText(this.reservation.getGuestMail());
            fld_phone_res.setText(this.reservation.getGuestPhone());
            fld_checkin_res.setText(this.reservation.getCheckInDate().toString());
            fld_checkout_res.setText(this.reservation.getCheckOutDate().toString());
        }

        this.fld_hotel_name_res.setText(this.hotel.getName());
        this.fld_city_res.setText(this.hotel.getAddress());
        this.fld_star_res.setText(this.hotel.getStar());
        this.rdo_carpark.setSelected(this.hotel.isCarPark());
        this.rdo_wifi.setSelected(this.hotel.isWifi());
        this.rdo_pool.setSelected(this.hotel.isPool());
        this.rdo_fitness.setSelected(this.hotel.isFitness());
        this.rdo_concierge.setSelected(this.hotel.isConcierge());
        this.rdo_spa.setSelected(this.hotel.isSpa());
        this.rdo_room_service.setSelected(this.hotel.isRoomService());
        this.fld_room_type_res.setText(this.room.getType());
        this.fld_pension_type_res.setText(this.pensionManager.getById(this.room.getPensionId()).getPensionType());
        this.fld_bed_capacity_res.setText(String.valueOf(this.room.getBedCapacity()));
        this.fld_square_meter_res.setText(String.valueOf(this.room.getSquareMeter()));
        this.rdo_tv_res.setSelected(this.room.isTelevision());
        this.rdo_gameconsole_res.setSelected(this.room.isGameConsole());
        this.rdo_projection_res.setSelected(this.room.isProjection());
        this.rdo_cashbox_res.setSelected(this.room.isCashBox());

        Helper.showMsg("Only Guest Info field can be edited except Total Guest Count.\n" +
                "Do not change Hotel and Room info fields.");

        this.saveButton();
    }
    private void saveButton(){
        btn_addreservation_res.addActionListener(e -> {
            {
                JTextField[] textfCheckList = {fld_guest_name_res, fld_city_res, fld_email_res, fld_phone_res};

                if(Helper.isFieldListEmpty(textfCheckList)){
                    Helper.showMsg("fill");
                }else{
                    boolean result = false;
                    this.reservation.setRoomId(this.room.getId());
                    this.reservation.setCheckInDate(LocalDate.parse(this.fld_checkin_res.getText()));
                    this.reservation.setCheckOutDate(LocalDate.parse(this.fld_checkout_res.getText()));
                    this.reservation.setTotalPrice(Double.parseDouble(this.fld_total_amount_res.getText()));
                    this.reservation.setGuestCount(Integer.parseInt(this.fld_guest_count_res.getText()));
                    this.reservation.setGuestName(this.fld_guest_name_res.getText());
                    this.reservation.setGuestCitizenId(this.fld_citizen_id_res.getText());
                    this.reservation.setGuestMail(this.fld_email_res.getText());
                    this.reservation.setGuestPhone(this.fld_phone_res.getText());

                    if(this.reservation.getId() == 0){
                        int stock = this.room.getStock();
                        if(this.reservationManager.save(this.reservation)){
                            Helper.showMsg("done");
                            stock -= 1;
                            this.roomManager.updateRoomStock(stock,this.room);
                            System.out.println(this.room.getStock());
                            dispose();
                        }else{
                            Helper.showMsg("error");
                        }
                    }else{
                        if(this.reservationManager.update(this.reservation)){
                            Helper.showMsg("done");
                            dispose();
                        }else{
                            Helper.showMsg("error");
                        }

                    }

                }
            }
        });
    }
    }


