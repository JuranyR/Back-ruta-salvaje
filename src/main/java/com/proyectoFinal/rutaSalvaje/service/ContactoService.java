package com.proyectoFinal.rutaSalvaje.service;

import com.proyectoFinal.rutaSalvaje.dto.ContactoRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ContactoResponseDTO;
import com.proyectoFinal.rutaSalvaje.mapper.ContactoMapper;
import com.proyectoFinal.rutaSalvaje.model.Contacto;
import com.proyectoFinal.rutaSalvaje.repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    public ContactoResponseDTO crearMensaje(ContactoRequestDTO mensaje) {

        Optional<Contacto> existeCorreo = contactoRepository.findByEmail(mensaje.getEmail().trim().toLowerCase());

        if (existeCorreo.isPresent()) {
            throw new IllegalArgumentException("Ya existe un mensaje con este email");
        }

        if (mensaje == null || mensaje.getMensaje().length() > 500) {
            throw new IllegalArgumentException("El mensaje no puede superar los 500 caracteres");
        }

        if (!mensaje.getTelefono().matches("\\d{7,10}")) {
            throw new IllegalArgumentException("El teléfono debe contener solo números entre 7 y 10 dígitos");
        }

        Contacto contacto = ContactoMapper.aEntidad(mensaje);

        Contacto guardarDB = contactoRepository.save(contacto);

        return ContactoMapper.aDTO(guardarDB);
    }

    public List<ContactoResponseDTO> listarMensajes () {
        List<Contacto> mensajes = contactoRepository.findAll();
        List<ContactoResponseDTO> mensajesDTO = new ArrayList<>();

        for (Contacto m : mensajes) {
            ContactoResponseDTO dto = ContactoMapper.aDTO(m);
            mensajesDTO.add(dto);
        }

        return mensajesDTO;
    }

    public ContactoResponseDTO buscarMensaje (Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        Contacto existeMensaje = contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El mensaje con ID " + id + " no existe"));

        return ContactoMapper.aDTO(existeMensaje);
    }

    public void eliminarMensaje (Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        Contacto existeMensaje = contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El mensaje con ID " + id + " no existe"));

        contactoRepository.deleteById(existeMensaje.getId());
    }
}
