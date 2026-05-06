package com.example.civicportal.controller;

import com.example.civicportal.dto.ComplaintRequest;
import com.example.civicportal.entity.Complaint;
import com.example.civicportal.enums.ComplaintStatus;
import com.example.civicportal.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService service;

    @PostMapping
    public String createComplaint(@RequestBody ComplaintRequest request) {

        // GET USER FROM JWT
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        service.createComplaint(request, username);

        return "Complaint submitted successfully";
    }

    @GetMapping("/my")
    public List<Complaint> my(Authentication auth) {
        return service.getUserComplaints(auth.getName());
    }

    @GetMapping("/all")
    public List<Complaint> all() {
        return service.getAllComplaints();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam ComplaintStatus status) {

        service.updateStatus(id, status);
        return "Updated";
    }
}
