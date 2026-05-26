package com.vetnova.soporteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetnova.soporteservice.model.SolicitudSoporte;

public interface SolicitudSoporteRepository extends JpaRepository<SolicitudSoporte, Long> {

    List<SolicitudSoporte> findByUsuarioId(Long usuarioId);

    List<SolicitudSoporte> findByEstado(String estado);
}