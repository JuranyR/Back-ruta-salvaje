package com.proyectoFinal.rutaSalvaje.repository;

import com.proyectoFinal.rutaSalvaje.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    //AQUI VAAAAAA CUALQIER MÉTODO EXTRA QUE  NECESITE
}
