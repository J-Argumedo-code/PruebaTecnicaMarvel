package com.marvel.model.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_history;
    private String user;
    private String action;
}
