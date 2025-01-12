package com.example.springboot.controller;

import com.example.springboot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @PostMapping("/vehicle")
    public List<Map<String, String>> getVehicle(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("addvehicle".equals(type)) {
            String reg_number = payload.get("reg_number");
            String veh_type = payload.get("veh_type");
            String veh_gear = payload.get("veh_gear");
            String make = payload.get("make");
            String vehicle_name = payload.get("vehicle_name");
            String model = payload.get("model");
            String reg_emirate = payload.get("reg_emirate");
            String plate_number = payload.get("plate_number");
            String purchase_type = payload.get("purchase_type");
            String purchase_company = payload.get("purchase_company");
            String initial_mileage = payload.get("initial_mileage");
            String mileage = payload.get("mileage");
            String rental_from = payload.get("rental_from");
            String rental_to = payload.get("rental_to");
            String lease_from = payload.get("lease_from");
            String lease_to = payload.get("lease_to");
            String replace_from = payload.get("replace_from");
            String replace_to = payload.get("replace_to");;
            String notes = payload.get("notes");
            String plan = payload.get("plan");
            String userid = payload.get("userid");

            return vehicleService.addVehicles(reg_number, veh_type, veh_gear, make, vehicle_name, model, reg_emirate, plate_number, purchase_type, purchase_company, initial_mileage,
                    mileage, rental_from, rental_to, lease_from, lease_to, replace_from, replace_to, notes, plan, userid);
        } else if ("list".equals(type)) {
            return vehicleService.getVehicleType(type);
        } else if("getbrandname".equals(type)) {
            String brand_id = payload.get("brand_id");

            return vehicleService.getBrandName(brand_id);
        } else if("getmakename".equals(type)) {
            String make_id = payload.get("make_id");

            return vehicleService.getMakeName(make_id);
        } else if("editvehicle".equals(type)) {
            String vehicle_id = payload.get("vehicle_id");

            return vehicleService.editVehicle(vehicle_id);
        } else if("updatevehicle".equals(type)) {
            String reg_number = payload.get("reg_number");
            String veh_type = payload.get("veh_type");
            String veh_gear = payload.get("veh_gear");
            String make = payload.get("make");
            String vehicle_name = payload.get("vehicle_name");
            String model = payload.get("model");
            String reg_emirate = payload.get("reg_emirate");
            String plate_number = payload.get("plate_number");
            String purchase_type = payload.get("purchase_type");
            String purchase_company = payload.get("purchase_company");
            String initial_mileage = payload.get("initial_mileage");
            String mileage = payload.get("mileage");
            String rental_from = payload.get("rental_from");
            String rental_to = payload.get("rental_to");
            String lease_from = payload.get("lease_from");
            String lease_to = payload.get("lease_to");
            String replace_from = payload.get("replace_from");
            String replace_to = payload.get("replace_to");;
            String notes = payload.get("notes");
            String plan = payload.get("plan");
            String userid = payload.get("userid");
            String id = payload.get("id");

            return vehicleService.updateVehicles(reg_number, veh_type, veh_gear, make, vehicle_name, model, reg_emirate, plate_number, purchase_type, purchase_company, initial_mileage,
                    mileage, rental_from, rental_to, lease_from, lease_to, replace_from, replace_to, notes, plan, userid, id);
        } else if ("list".equals(type)) {
            return vehicleService.getVehicleTypeList();
        } else {
            return null;
        }
    }

    @PostMapping("/getVehicles")
    public List<Map<String, String>> getVehicles(@RequestBody Map<String, String> payload) {
        String branch = payload.get("branch");
        String user_role = payload.get("user_role");
        String status = payload.get("status");
        String type = payload.get("type");
        String type2 = payload.get("type2");

        return vehicleService.getVehicles(branch, user_role, status, type, type2);
    }

    @PostMapping("/vehicleType")
    public List<Map<String, String>> getVehicleType(@RequestBody Map<String, String> payload) {
        String user = payload.get("user");
        String type = payload.get("type");

        if ("list".equals(type)) {
            return vehicleService.getVehicleTypes();
        } else if ("off".equals(type)) {
            return vehicleService.getOffVehicles();
        } else if ("add".equals(type)) {
            String veh_type = payload.get("veh_type");
            String km_allowance = payload.get("km_allowance");
            String fuel_allowance = payload.get("fuel_allowance");
            return vehicleService.createVehicleType(veh_type, km_allowance, fuel_allowance);
        } else if ("update".equals(type)) {
            String veh_type = payload.get("veh_type");
            String km_allowance = payload.get("km_allowance");
            String fuel_allowance = payload.get("fuel_allowance");
            String id = payload.get("id");

            return vehicleService.updateVehicleType(id, veh_type, km_allowance, fuel_allowance);
        } else if ("delete".equals(type)) {
            String record = payload.get("record");
            return vehicleService.deleteVehicleType(record);
        } else if ("details".equals(type)) {
            String vehicletypeId = payload.get("vehicletypeid");
            return vehicleService.getVehicleTypeDetail(vehicletypeId);
        } else if ("restore".equals(type)) {
            String record = payload.get("record");
            return vehicleService.restoreVehicle(user, record);
        } else {
            return null;
        }
    }

    @PostMapping("/vehicleName")
    public List<Map<String, String>> getVehicleName(@RequestBody Map<String, String> payload) {
        String user = payload.get("user");
        String type = payload.get("type");

        if ("delete".equals(type)) {
            String record = payload.get("record");
            return vehicleService.deleteVehicleName(record);
        } else if ("list".equals(type)) {
            return vehicleService.getVehicleName();
        } else if ("add".equals(type)) {
            String veh_name = payload.get("veh_name");
            String brandId = payload.get("brandId");
            return vehicleService.createVehicleName(veh_name, brandId);
        } else if ("details".equals(type)) {
            String vehicleNameId = payload.get("id");
            return vehicleService.getVehicleNameDetail(vehicleNameId);
        } else if ("update".equals(type)) {
            String veh_name = payload.get("veh_name");
            String brandId = payload.get("brandId");
            String id = payload.get("id");

            logger.info(">>>>>>>>" + id + brandId + veh_name);

            return vehicleService.updateVehicleName(id, veh_name, brandId);
        } else {
            return null;
        }
    }

    @PostMapping("/vehicleAction")
    public List<Map<String, String>> getvehicleAction(@RequestBody Map<String, String> payload) {
        String user = payload.get("user");
        String type = payload.get("type");

        if ("old".equals(type)) {
            return vehicleService.getVehicleActionOld();
        } else if("delete".equals(type)) {
            String record = payload.get("record");
            return vehicleService.deleteVehicle(record, user);
        } else if("search".equals(type)) {
            String searchData = payload.get("searchdata");
            return vehicleService.searchVehicle(searchData);
        } else {
            return null;
        }
    }

    @PostMapping("/vehicelActionDetails")
    public Map<String, List<Map<String, String>>> getVehicleDetails(@RequestBody Map<String, String> payload) {
        String vehicleId = payload.get("record");

        return vehicleService.getVehicleDetails(vehicleId);
    }

    @PostMapping("/vehicleActionGetBranch")
    public Map<String, List<Map<String, String>>> getBranchVehicleAction(@RequestBody Map<String, String> payload) {
        String driverId = payload.get("driverid");
        String vehicleId = payload.get("vehicle");

        return vehicleService.getBranchVehicleAction(driverId, vehicleId);
    }

    @PostMapping("/brandList")
    public List<Map<String, String>> getBrandList() {
        return vehicleService.getBrandList();
    }
}