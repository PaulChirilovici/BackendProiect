package com.example.backendproiect.entities;

import com.example.backendproiect.dao.UserDAO;
import com.example.backendproiect.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    private String email;
    private String password;
}
