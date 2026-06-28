package cl.patrones.taller.u2.tienda.menu;

import java.util.List;

public class LinkItemMenu implements ItemMenu{
    private String texto;
    private String enlace;
    private List<? extends ItemMenu> hijos;

    public LinkItemMenu(String texto, String enlace) {
        this(texto, enlace, List.of());
    }

    public LinkItemMenu(String texto, String enlace, List<? extends ItemMenu> hijos) {
        this.texto = texto;
        this.enlace = enlace;
        this.hijos = hijos;
    }

    @Override
    public String getTexto() {
        return texto;
    }

    @Override
    public String getSlug() {
        return "";
    }

    @Override
    public String getEnlace() {
        return enlace;
    }

    @Override
    public boolean tieneHijos() {
        return hijos != null && !hijos.isEmpty();
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return hijos;
    }
}
