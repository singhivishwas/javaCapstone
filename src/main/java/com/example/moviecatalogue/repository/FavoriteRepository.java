package com.example.moviecatalogue.repository;

import com.example.moviecatalogue.model.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteMovie, Integer> {
}
