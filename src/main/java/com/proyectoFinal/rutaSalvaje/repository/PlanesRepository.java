package com.proyectoFinal.rutaSalvaje.repository;

import com.proyectoFinal.rutaSalvaje.model.Planes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanesRepository  extends JpaRepository<Planes, Long> {
    //AQUI VAAAAAA CUALQIER MÉTODO EXTRA QUE  NECESITE
}
