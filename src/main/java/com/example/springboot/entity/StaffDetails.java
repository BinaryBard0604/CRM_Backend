package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "staff_details")
public class StaffDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String branch;

    @Column(nullable = false, length = 200)
    private String firstName;

    @Column(nullable = false, length = 200)
    private String lastName;

    private LocalDate dob;

    private String designation;

    @Column(nullable = false, length = 200)
    private String common_name;

    @Column(nullable = false, length = 200)
    private String employee_number;

    @Column(nullable = false, length = 200)
    private String phone;

    @Column(nullable = false, length = 200)
    private String home_contact;

    @Column(nullable = false, length = 200)
    private String home_contact_no;

    private LocalDate date_employed;

    @Column(nullable = false, length = 200)
    private String citizenship;

    private String passport_number;

    private String change_date;

    private String license_type;

    private LocalDate license_expiry_date;

    private LocalDate pdrp_expiry_date;

    private String work_permit_number;

    private LocalDate work_permit_expiry;

    @Column(nullable = false, length = 200)
    private String ctc;

    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String res_address;

    @Column(nullable = false, length = 200)
    private String res_city;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String passport;

    @Column(nullable = false, length = 200)
    private String visa_type;

    @Column(nullable = false, length = 200)
    private String own_vehicle;

    @Column(columnDefinition = "TEXT")
    private String visa;

    @Column(columnDefinition = "TEXT")
    private String driving_license;

    @Column(columnDefinition = "TEXT")
    private String emirates_id;

    @Column(columnDefinition = "TEXT")
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String labour_contract;

    @Column(columnDefinition = "TEXT")
    private String other_doc;

    @Column(nullable = false, length = 200)
    private String emirates_id_no;

    @Column(nullable = false, length = 200)
    private String license_no;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer updated_by;

    private String updated_date;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHome_contact() {
        return home_contact;
    }

    public void setHome_contact(String home_contact) {
        this.home_contact = home_contact;
    }

    public String getHome_contact_no() {
        return home_contact_no;
    }

    public void setHome_contact_no(String home_contact_no) {
        this.home_contact_no = home_contact_no;
    }

    public LocalDate getDate_employed() {
        return date_employed;
    }

    public void setDate_employed(LocalDate date_employed) {
        this.date_employed = date_employed;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getChange_date() {
        return change_date;
    }

    public void setChange_date(String change_date) {
        this.change_date = change_date;
    }

    public String getLicense_type() {
        return license_type;
    }

    public void setLicense_type(String license_type) {
        this.license_type = license_type;
    }

    public LocalDate getLicense_expiry_date() {
        return license_expiry_date;
    }

    public void setLicense_expiry_date(LocalDate license_expiry_date) {
        this.license_expiry_date = license_expiry_date;
    }

    public LocalDate getPdrp_expiry_date() {
        return pdrp_expiry_date;
    }

    public void setPdrp_expiry_date(LocalDate pdrp_expiry_date) {
        this.pdrp_expiry_date = pdrp_expiry_date;
    }

    public String getWork_permit_number() {
        return work_permit_number;
    }

    public void setWork_permit_number(String work_permit_number) {
        this.work_permit_number = work_permit_number;
    }

    public LocalDate getWork_permit_expiry() {
        return work_permit_expiry;
    }

    public void setWork_permit_expiry(LocalDate work_permit_expiry) {
        this.work_permit_expiry = work_permit_expiry;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRes_address() {
        return res_address;
    }

    public void setRes_address(String res_address) {
        this.res_address = res_address;
    }

    public String getRes_city() {
        return res_city;
    }

    public void setRes_city(String res_city) {
        this.res_city = res_city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getVisa_type() {
        return visa_type;
    }

    public void setVisa_type(String visa_type) {
        this.visa_type = visa_type;
    }

    public String getOwn_vehicle() {
        return own_vehicle;
    }

    public void setOwn_vehicle(String own_vehicle) {
        this.own_vehicle = own_vehicle;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getDriving_license() {
        return driving_license;
    }

    public void setDriving_license(String driving_license) {
        this.driving_license = driving_license;
    }

    public String getEmirates_id() {
        return emirates_id;
    }

    public void setEmirates_id(String emirates_id) {
        this.emirates_id = emirates_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLabour_contract() {
        return labour_contract;
    }

    public void setLabour_contract(String labour_contract) {
        this.labour_contract = labour_contract;
    }

    public String getOther_doc() {
        return other_doc;
    }

    public void setOther_doc(String other_doc) {
        this.other_doc = other_doc;
    }

    public String getEmirates_id_no() {
        return emirates_id_no;
    }

    public void setEmirates_id_no(String emirates_id_no) {
        this.emirates_id_no = emirates_id_no;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
