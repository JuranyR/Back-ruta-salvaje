package com.proyectoFinal.rutaSalvaje.repository;

import com.proyectoFinal.rutaSalvaje.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
}
