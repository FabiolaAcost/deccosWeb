package cl.patrones.taller.u2.catalogo.service;

import java.util.List;
import java.util.Optional;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.domain.Clasificacion;

public interface CategoriaService {

	public List<Categoria> getCategorias();
	public Optional<Categoria> getCategoriaPorId(Long id);
	public Optional<Clasificacion> getClasificacionPorSku(String sku);

	public List<Clasificacion> getClasificacionesPorCategoriaId(Long categoriaId);
}
