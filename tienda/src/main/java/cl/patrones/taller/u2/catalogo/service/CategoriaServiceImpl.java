package cl.patrones.taller.u2.catalogo.service;

import java.util.List;
import java.util.Optional;

import cl.patrones.taller.u2.catalogo.domain.Clasificacion;
import cl.patrones.taller.u2.catalogo.repository.ClasificacionRepository;
import org.springframework.stereotype.Service;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaRepository repository;
	private ClasificacionRepository clasificacionRepository;
	
	public CategoriaServiceImpl(CategoriaRepository repository, ClasificacionRepository clasificacionRepository) {
		super();
		this.repository = repository;
		this.clasificacionRepository = clasificacionRepository;
	}
	
	@Override
	public List<Categoria> getCategorias() {
		return repository.findAll();
	}

	@Override
	public Optional<Categoria> getCategoriaPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Clasificacion> getClasificacionPorSku(String sku) {
		return clasificacionRepository.findFirstBySku(sku);
	}

	@Override
	public List<Clasificacion> getClasificacionesPorCategoriaId(Long categoriaId) {
		return clasificacionRepository.findByCategoriaId(categoriaId);
	}

}
