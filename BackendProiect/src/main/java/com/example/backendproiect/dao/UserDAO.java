package com.example.backendproiect.dao;

import com.example.backendproiect.dto.UserDto;
import com.example.backendproiect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,String> {

}
