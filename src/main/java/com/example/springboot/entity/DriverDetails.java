package com.example.springboot.entity;

import com.example.springboot.converter.DateAttributeConverter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "driver_details")
public class DriverDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String branch;

    @Column(nullable = false, length = 200)
    private String firstName;

    @Column(nullable = false, length = 200)
    private String lastName;

    @Column(nullable = false, length = 200)
    private String commonName;

    @Column(nullable = false, length = 200)
    private String employeeNumber;

    @Column(nullable = false, length = 200)
    private String phone;

    @Column(nullable = false, length = 200)
    private String homeContact;

    @Column(nullable = false, length = 200)
    private String homeContactNo;

    private LocalDate dateEmployed;

    @Column(nullable = false, length = 200)
    private String citizenship;

    private String passportNumber;

    private String changeDate;

    private String licenseType;

    private LocalDate licenseExpiryDate;

    @Column(name = "pdrp_expiry_date")
    private LocalDate pdrpExpiryDate;

    private String workPermitNumber;

    private LocalDate workPermitExpiry;

    @Column(nullable = false, length = 200)
    private String ctc;

    @Column(length = 200)
    private String cpk = "0";

    @Column(columnDefinition = "TEXT")
    private String resAddress;

    @Column(nullable = false, length = 200)
    private String resCity;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String passport;

    @Column(nullable = false, length = 200)
    private String visaType;

    @Column(nullable = false, length = 200)
    private String ownVehicle;

    @Column(columnDefinition = "TEXT")
    private String visa;

    @Column(columnDefinition = "TEXT")
    private String drivingLicense;

    @Column(columnDefinition = "TEXT")
    private String emiratesId;

    @Column(columnDefinition = "TEXT")
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String labourContract;

    @Column(columnDefinition = "TEXT")
    private String otherDoc;

    private LocalDate dob;

    @Column(nullable = false, length = 200)
    private String emiratesIdNo;

    @Column(nullable = false, length = 200)
    private String licenseNo;

    @Column(nullable = false)
    private int status = 1; // Default value is 1

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false)
    private LocalDate updatedDate;

    public DriverDetails() {}

    public DriverDetails(
            String branch,
            String firstName,
            String lastName,
            String commonName,
            String employeeNumber,
            String phone,
            String homeContact,
            String homeContactNo,
            LocalDate dateEmployed,
            String citizenship,
            String passportNumber,
            String changeDate,
            String licenseType,
            LocalDate licenseExpiryDate,
            LocalDate pdrpExpiryDate,
            String workPermitNumber,
            LocalDate workPermitExpiry,
            String ctc,
            String cpk,
            String resAddress,
            String resCity,
            String notes,
            String passport,
            String visaType,
            String ownVehicle,
            String visa,
            String drivingLicense,
            String emiratesId,
            String photo,
            String labourContract,
            String otherDoc,
            LocalDate dob,
            String emiratesIdNo,
            String licenseNo,
            int status,
            int updatedBy,
            LocalDate updatedDate) {
        this.branch = branch;
        this.firstName = firstName;
        this.lastName = lastName;
        this.commonName = commonName;
        this.employeeNumber = employeeNumber;
        this.phone = phone;
        this.homeContact = homeContact;
        this.homeContactNo = homeContactNo;
        this.dateEmployed = dateEmployed;
        this.citizenship = citizenship;
        this.passportNumber = passportNumber;
        this.changeDate = changeDate;
        this.licenseType = licenseType;
        this.licenseExpiryDate = licenseExpiryDate;
        this.pdrpExpiryDate = pdrpExpiryDate;
        this.workPermitNumber = workPermitNumber;
        this.workPermitExpiry = workPermitExpiry;
        this.ctc = ctc;
        this.cpk = cpk;
        this.resAddress = resAddress;
        this.resCity = resCity;
        this.notes = notes;
        this.passport = passport;
        this.visaType = visaType;
        this.ownVehicle = ownVehicle;
        this.visa = visa;
        this.drivingLicense = drivingLicense;
        this.emiratesId = emiratesId;
        this.photo = photo;
        this.labourContract = labourContract;
        this.otherDoc = otherDoc;
        this.dob = dob;
        this.emiratesIdNo = emiratesIdNo;
        this.licenseNo = licenseNo;
        this.status = status;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeContact() {
        return homeContact;
    }

    public void setHomeContact(String homeContact) {
        this.homeContact = homeContact;
    }

    public String getHomeContactNo() {
        return homeContactNo;
    }

    public void setHomeContactNo(String homeContactNo) {
        this.homeContactNo = homeContactNo;
    }

    public LocalDate getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(LocalDate dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public LocalDate getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(LocalDate licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public LocalDate getPdrpExpiryDate() {
        return pdrpExpiryDate;
    }

    public void setPdrpExpiryDate(LocalDate pdrpExpiryDate) {
        // Check if the date is invalid and set to null
        if (pdrpExpiryDate != null && pdrpExpiryDate.toString().equals("0000-00-00")) {
            this.pdrpExpiryDate = null;
        } else {
            this.pdrpExpiryDate = pdrpExpiryDate;
        }
    }

    public String getWorkPermitNumber() {
        return workPermitNumber;
    }

    public void setWorkPermitNumber(String workPermitNumber) {
        this.workPermitNumber = workPermitNumber;
    }

    public LocalDate getWorkPermitExpiry() {
        return workPermitExpiry;
    }

    public void setWorkPermitExpiry(LocalDate workPermitExpiry) {
        this.workPermitExpiry = workPermitExpiry;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getCpk() {
        return cpk;
    }

    public void setCpk(String cpk) {
        this.cpk = cpk;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public String getResCity() {
        return resCity;
    }

    public void setResCity(String resCity) {
        this.resCity = resCity;
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

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getOwnVehicle() {
        return ownVehicle;
    }

    public void setOwnVehicle(String ownVehicle) {
        this.ownVehicle = ownVehicle;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getEmiratesId() {
        return emiratesId;
    }

    public void setEmiratesId(String emiratesId) {
        this.emiratesId = emiratesId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLabourContract() {
        return labourContract;
    }

    public void setLabourContract(String labourContract) {
        this.labourContract = labourContract;
    }

    public String getOtherDoc() {
        return otherDoc;
    }

    public void setOtherDoc(String otherDoc) {
        this.otherDoc = otherDoc;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmiratesIdNo() {
        return emiratesIdNo;
    }

    public void setEmiratesIdNo(String emiratesIdNo) {
        this.emiratesIdNo = emiratesIdNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
