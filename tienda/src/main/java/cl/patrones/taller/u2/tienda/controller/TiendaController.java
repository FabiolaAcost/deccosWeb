package cl.patrones.taller.u2.tienda.controller;

import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.service.TiendaAvisoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TiendaController {
	private TiendaAvisoService tiendaAvisoService;
	private CategoriaService categoriaService;
	public TiendaController(
			TiendaAvisoService tiendaAvisoService,
			CategoriaService categoriaService
	) {
		this.tiendaAvisoService = tiendaAvisoService;
		this.categoriaService = categoriaService;
	}
	@GetMapping("/")
	public String inicio(Model model) {
		var avisos = tiendaAvisoService.getAvisos();
		model.addAttribute("avisos", avisos);
		return "inicio";
	}
		
	@GetMapping("/categoria/{categoriaId}/{slug}")
	public String categoria(
			@PathVariable(name = "categoriaId") Long categoriaId,
			@PathVariable(name = "slug") String slug,
			Model model
	) {
		var avisos = tiendaAvisoService.getAvisosPorCategoriaId(categoriaId);
		var categoria = categoriaService.getCategoriaPorId(categoriaId).orElse(null);

		model.addAttribute("avisos", avisos);
		model.addAttribute("categoria", categoria);
		return "categoria";
	}
	
	@GetMapping("/ingresar")
	public String login() {
		return "login";
	}
	
	@GetMapping("/ubicacion")
	public String ubicacion() {return "ubicacion";}
	
	@GetMapping("/contacto")
	public String contacto() {return "contacto";}
	
}
