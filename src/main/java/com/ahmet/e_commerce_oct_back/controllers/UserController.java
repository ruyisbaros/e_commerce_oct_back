package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.UserDto;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.entities.Role;
import com.ahmet.e_commerce_oct_back.exporters.UserCsvExporter;
import com.ahmet.e_commerce_oct_back.exporters.UserExcelExporter;
import com.ahmet.e_commerce_oct_back.exporters.UserPdfExporter;
import com.ahmet.e_commerce_oct_back.repositories.UserRep;
import com.ahmet.e_commerce_oct_back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "https://shopwithahmet.netlify.app",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private UserRep userRep;

    @GetMapping("/user/get_user/{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long userId) {
        AppUser foundUser = userService.findUser(userId);

        return ResponseEntity.ok(foundUser);
    }
    @GetMapping("/admin/all")
    public Page<AppUser> getUsers(
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "sortDir", defaultValue = "id", required = false) String sortDir,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword
    ) {
        return userService.getAllUsers(pageSize, pageNo, sortDir, sortField, keyword);
    }
    @GetMapping("/user/is_email_unique/{email}")
    public boolean check_email(@PathVariable String email) {
        return userRep.findByEmail(email).isPresent();
    }


    @DeleteMapping("/admin/delete_user/{userId}")
    public void deleteUser(@PathVariable long userId) throws IOException {
        userService.deleteUser(userId);
    }

    //update general
    @PutMapping("/admin/update_user_admin/{userId}")
    public AppUser updateUser(@RequestBody UserDto request, @PathVariable Long userId) {
        return userService.updateUser(userId, request);
    }

    //Update Enabled-disabled
    @PutMapping("/admin/user_enabled_disabled/{userId}")
    public void updateEnableSt(@PathVariable long userId) {
        userService.updateEnableStatus(userId);
    }

    @GetMapping("/admin/get_roles")
    public List<Role> getRoles() {
        return userService.findAllRoles();
    }

    //Export Users info as CSV Format
    @GetMapping("/admin/export_csv")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        List<AppUser> users = userService.listAllUsers();
        UserCsvExporter exporter = new UserCsvExporter();
        exporter.export(users, response);
    }

    //Export Users info as CSV Format
    @GetMapping("/admin/export_excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<AppUser> users = userService.listAllUsers();
        UserExcelExporter exporter = new UserExcelExporter();
        exporter.export(users, response);
    }

    //Export Users info as CSV Format
    @GetMapping("/admin/export_pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        List<AppUser> users = userService.listAllUsers();
        UserPdfExporter exporter = new UserPdfExporter();
        exporter.export(users, response);
    }
}
