package com.axis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.entity.Image;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
	Optional<Image> findByFileName(String filename);

}
