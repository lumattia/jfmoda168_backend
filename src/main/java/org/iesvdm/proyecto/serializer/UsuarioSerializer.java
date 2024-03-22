package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.domain.Estudiante;
import org.iesvdm.proyecto.domain.Profesor;
import org.iesvdm.proyecto.domain.Usuario;

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
        jgen.writeStringField("nombre_completo", (usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()).trim());
        jgen.writeStringField("rol", usuario.getRol());
        if (usuario instanceof Profesor) {
            Profesor profesor = (Profesor) usuario;
            jgen.writeFieldName("clase");
            jgen.writeObject(profesor.getClases().stream().map(clase -> clase.getCurso().getNombre() + " " + clase.getAsignatura().getNombre()).toList());
        }
        if (usuario instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) usuario;
            jgen.writeStringField("nicknName",estudiante.getNickName());
        }
        jgen.writeEndObject();
    }
}