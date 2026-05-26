package com.vetnova.soporteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vetnova.soporteservice.model.SolicitudSoporte;
import com.vetnova.soporteservice.service.SolicitudSoporteService;

@RestController
@RequestMapping("/api/soporte")
public class SolicitudSoporteController {

    @Autowired
    private SolicitudSoporteService solicitudSoporteService;

    @GetMapping
    public List<SolicitudSoporte> obtenerSolicitudes() {
        return solicitudSoporteService.obtenerSolicitudes();
    }

    @GetMapping("/{id}")
    public SolicitudSoporte obtenerSolicitudPorId(@PathVariable Long id) {
        return solicitudSoporteService.obtenerSolicitudPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<SolicitudSoporte> obtenerSolicitudesPorUsuario(@PathVariable Long usuarioId) {
        return solicitudSoporteService.obtenerSolicitudesPorUsuario(usuarioId);
    }

    @GetMapping("/estado/{estado}")
    public List<SolicitudSoporte> obtenerSolicitudesPorEstado(@PathVariable String estado) {
        return solicitudSoporteService.obtenerSolicitudesPorEstado(estado);
    }

    @PostMapping
    public SolicitudSoporte guardarSolicitud(@RequestBody SolicitudSoporte solicitud) {
        return solicitudSoporteService.guardarSolicitud(solicitud);
    }

    @PutMapping("/{id}")
    public SolicitudSoporte actualizarSolicitud(@PathVariable Long id,
                                                @RequestBody SolicitudSoporte solicitud) {
        return solicitudSoporteService.actualizarSolicitud(id, solicitud);
    }

    @DeleteMapping("/{id}")
    public String eliminarSolicitud(@PathVariable Long id) {
        boolean eliminado = solicitudSoporteService.eliminarSolicitud(id);

        if (eliminado) {
            return "Solicitud de soporte eliminada correctamente";
        }

        return "Solicitud de soporte no encontrada";
    }
}