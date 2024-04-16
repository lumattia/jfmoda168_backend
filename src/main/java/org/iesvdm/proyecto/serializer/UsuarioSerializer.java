package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Usuario;

import java.io.IOException;

public class UsuarioSerializer extends StdSerializer<Usuario> {
    public UsuarioSerializer() {
        this(null);
    }

    public UsuarioSerializer(Class<Usuario> t) {
        super(t);
    }

    @Override
    public void serialize(Usuario usuario, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", usuario.getId());
        jgen.writeStringField("email", usuario.getEmail());
        jgen.writeStringField("nombre_completo", usuario.getNombreCompleto());
        jgen.writeStringField("nombre", usuario.getNombre());
        jgen.writeStringField("apellidos", usuario.getApellidos());
        jgen.writeStringField("rol", usuario.getRol());
        jgen.writeBooleanField("blocked", usuario.isBlocked());
        if (usuario instanceof Profesor profesor) {
            jgen.writeFieldName("clases");
            jgen.writeObject(profesor.getClases().stream().map(clase -> clase.getCurso().getNombre() + " " + clase.getAsignatura().getNombre()).toList());
        }
        if (usuario instanceof Estudiante estudiante) {
            jgen.writeStringField("aula",estudiante.getAula());
        }
        jgen.writeEndObject();
    }
}
