package com.proyectoFinal.rutaSalvaje.mapper;

import com.proyectoFinal.rutaSalvaje.dto.ContactoRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ContactoResponseDTO;
import com.proyectoFinal.rutaSalvaje.model.Contacto;

public class ContactoMapper {

    public static Contacto aEntidad(ContactoRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Contacto contacto = new Contacto();

        contacto.setNombre(dto.getNombre());
        contacto.setEmail(dto.getEmail());
        contacto.setTelefono(dto.getTelefono());
        contacto.setMensaje(dto.getMensaje());

        return contacto;
    }

    public static ContactoResponseDTO aDTO(Contacto contacto) {

        if (contacto == null) {
            return null;
        }

        ContactoResponseDTO dto = new ContactoResponseDTO();

        dto.setId(contacto.getId());
        dto.setNombre(contacto.getNombre());
        dto.setEmail(contacto.getEmail());
        dto.setTelefono(contacto.getTelefono());
        dto.setMensaje(contacto.getMensaje());

        return dto;
    }
}
