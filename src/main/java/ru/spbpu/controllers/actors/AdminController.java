package ru.spbpu.controllers.actors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.spbpu.models.system.Deal;
import ru.spbpu.services.AdminService;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("approve/{dealId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void approve(@PathVariable Long dealId) {
        adminService.approve(dealId);
    }

    @GetMapping("getDeals")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Deal>> getDeals(@RequestParam Long id) {
        return adminService.getDeals(id)
                .map(administrator -> new ResponseEntity<>(administrator.getDeals(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
