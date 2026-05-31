package com.proyectoFinal.rutaSalvaje.repository;

import com.proyectoFinal.rutaSalvaje.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    Optional<Contacto> findByEmail(String email);
}
