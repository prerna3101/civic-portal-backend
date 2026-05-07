package com.example.civicportal.entity;

import com.example.civicportal.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.PENDING; // ✅ default

    private String username;
}