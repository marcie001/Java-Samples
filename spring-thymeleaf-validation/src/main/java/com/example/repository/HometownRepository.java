package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Hometown;

@Repository
public interface HometownRepository extends JpaRepository<Hometown, String> {

	long countByName(String name);
}
