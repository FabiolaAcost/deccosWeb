package cl.patrones.taller.u2.tienda.controller;


import java.util.List;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.tienda.menu.CategoriaItemMenuAdapter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.menu.LinkItemMenu;

@ControllerAdvice
public class MenuControllerAdvice {

	private CategoriaService categoriaService;

	public MenuControllerAdvice(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@ModelAttribute("menu")
	public List<ItemMenu> menu() {
		List<Categoria> categorias = categoriaService.getCategorias();

		List<CategoriaItemMenuAdapter> categoriasMenu = crearMenuCategorias(categorias, null);

		return List.of(
				new LinkItemMenu("Inicio", "/"),
				new LinkItemMenu("Categorías", "/categoria", categoriasMenu),
				new LinkItemMenu("Ubicación", "/ubicacion"),
				new LinkItemMenu("Contacto", "/contacto")
		);
	}
	private List<CategoriaItemMenuAdapter> crearMenuCategorias(List<Categoria> categorias, Categoria padre) {
		return categorias.stream()
				.filter(categoria -> {
					if (padre == null) {
						return categoria.getPadre() == null;
					}
					return categoria.getPadre() != null && categoria.getPadre().getId().equals(padre.getId());
				})
				.map(categoria -> new CategoriaItemMenuAdapter(
						categoria,
						crearMenuCategorias(categorias, categoria)
				))
				.toList();
	}
}
