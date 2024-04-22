package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Clase;
import org.iesvdm.proyecto.model.entity.Profesor;

import java.io.IOException;

public class ClaseSerializer extends StdSerializer<Clase> {
    public ClaseSerializer() {
        this(null);
    }
    public ClaseSerializer(Class<Clase> t) {
        super(t);
    }

    @Override
    public void serialize(Clase clase, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", clase.getId());
        jgen.writeStringField("nombre", clase.getNombre());
        if (clase.getProfesores()!=null){
            jgen.writeFieldName("profesores");
            jgen.writeObject(clase.getProfesores().stream().map(Profesor::getNombreCompleto));
        }
        if (clase.getAulas()!=null){
            jgen.writeFieldName("aulas");
            jgen.writeObject(clase.getAulas().stream().map(Aula::getNombre));
        }
        jgen.writeEndObject();

    }
}
