package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.domain.Clase;
import org.iesvdm.proyecto.domain.Profesor;

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
        jgen.writeStringField("nombre", clase.getAsignatura().getNombre()+" "+clase.getCurso().getNombre());
        if (clase.getProfesores()!=null){
            jgen.writeFieldName("profesores");
            jgen.writeObject(clase.getProfesores().stream().map(Profesor::getNombre));
        }
        jgen.writeEndObject();

    }
}
