package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.model.Estudiante;
import org.iesvdm.proyecto.model.Profesor;
import org.iesvdm.proyecto.model.Tarea;

import java.io.IOException;

public class TareaSerializer extends StdSerializer<Tarea> {
    public TareaSerializer() {
        this(null);
    }

    public TareaSerializer(Class<Tarea> t) {
        super(t);
    }

    @Override
    public void serialize(Tarea tarea, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", tarea.getId());
        jgen.writeStringField("propietario", tarea.getPropietario().getNombreCompleto());
        jgen.writeEndObject();
    }
}
