package org.iesvdm.proyecto.exeption;

public class DuplicateEntityException extends RuntimeException{
    private final Object entity;
    public DuplicateEntityException(Object entity, Throwable cause) {
        super(cause);
        this.entity = entity;
    }
    public Object getEntity() {
        return entity;
    }
}
