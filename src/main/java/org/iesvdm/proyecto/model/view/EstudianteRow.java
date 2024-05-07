package org.iesvdm.proyecto.model.view;

public interface EstudianteRow {
    Long getId();
    String getNombre();
    String getApellidos();
    String getEmail();
    String getAula();
    boolean isBlocked();
}
