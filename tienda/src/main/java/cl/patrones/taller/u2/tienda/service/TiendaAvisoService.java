package cl.patrones.taller.u2.tienda.service;

import cl.patrones.taller.u2.catalogo.domain.Aviso;

import java.util.List;

public interface TiendaAvisoService {
    List<Aviso> getAvisos();

    List<Aviso> getAvisosPorCategoriaId(Long categoriaId);
}
