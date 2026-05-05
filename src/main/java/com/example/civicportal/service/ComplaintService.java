package com.example.civicportal.service;

import com.example.civicportal.dto.ComplaintRequest;
import com.example.civicportal.entity.Complaint;
import com.example.civicportal.enums.ComplaintStatus;

import java.util.List;

public interface ComplaintService {

    void createComplaint(ComplaintRequest request, String username);

    List<Complaint> getUserComplaints(String username);

    List<Complaint> getAllComplaints();

    void updateStatus(Long id, ComplaintStatus status);
}
