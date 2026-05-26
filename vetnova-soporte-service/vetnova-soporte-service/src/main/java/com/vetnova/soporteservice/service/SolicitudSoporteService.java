package com.vetnova.soporteservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vetnova.soporteservice.model.SolicitudSoporte;
import com.vetnova.soporteservice.repository.SolicitudSoporteRepository;

@Service
public class SolicitudSoporteService {

    @Autowired
    private SolicitudSoporteRepository solicitudSoporteRepository;

    public List<SolicitudSoporte> obtenerSolicitudes() {
        return solicitudSoporteRepository.findAll();
    }

    public SolicitudSoporte obtenerSolicitudPorId(Long id) {
        return solicitudSoporteRepository.findById(id).orElse(null);
    }

    public List<SolicitudSoporte> obtenerSolicitudesPorUsuario(Long usuarioId) {
        return solicitudSoporteRepository.findByUsuarioId(usuarioId);
    }

    public List<SolicitudSoporte> obtenerSolicitudesPorEstado(String estado) {
        return solicitudSoporteRepository.findByEstado(estado);
    }

    public SolicitudSoporte guardarSolicitud(SolicitudSoporte solicitud) {
        solicitud.setFechaCreacion(LocalDateTime.now());

        if (solicitud.getEstado() == null || solicitud.getEstado().isBlank()) {
            solicitud.setEstado("ABIERTA");
        }

        if (solicitud.getPrioridad() == null || solicitud.getPrioridad().isBlank()) {
            solicitud.setPrioridad("NORMAL");
        }

        return solicitudSoporteRepository.save(solicitud);
    }

    public SolicitudSoporte actualizarSolicitud(Long id, SolicitudSoporte solicitudActualizada) {
        Optional<SolicitudSoporte> solicitudExistente = solicitudSoporteRepository.findById(id);

        if (solicitudExistente.isPresent()) {
            SolicitudSoporte solicitud = solicitudExistente.get();

            solicitud.setUsuarioId(solicitudActualizada.getUsuarioId());
            solicitud.setAsunto(solicitudActualizada.getAsunto());
            solicitud.setDescripcion(solicitudActualizada.getDescripcion());
            solicitud.setEstado(solicitudActualizada.getEstado());
            solicitud.setPrioridad(solicitudActualizada.getPrioridad());

            return solicitudSoporteRepository.save(solicitud);
        }

        return null;
    }

    public boolean eliminarSolicitud(Long id) {
        if (solicitudSoporteRepository.existsById(id)) {
            solicitudSoporteRepository.deleteById(id);
            return true;
        }

        return false;
    }
}