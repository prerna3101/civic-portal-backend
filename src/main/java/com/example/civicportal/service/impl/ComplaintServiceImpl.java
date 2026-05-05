package com.example.civicportal.service.impl;

import com.example.civicportal.dto.ComplaintRequest;
import com.example.civicportal.entity.Complaint;
import com.example.civicportal.enums.ComplaintStatus;
import com.example.civicportal.repository.ComplaintRepository;
import com.example.civicportal.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;

    @Override
    public void createComplaint(ComplaintRequest request, String username) {

        Complaint c = new Complaint();
        c.setTitle(request.getTitle());
        c.setDescription(request.getDescription());
        c.setStatus(ComplaintStatus.PENDING);
        c.setUsername(username);

        repo.save(c);
    }

    @Override
    public List<Complaint> getUserComplaints(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    @Override
    public void updateStatus(Long id, ComplaintStatus status) {
        Complaint c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        c.setStatus(status);
        repo.save(c);
    }
}
