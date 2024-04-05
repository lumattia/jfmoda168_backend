package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.model.*;

import java.io.IOException;

public class TareaEstudianteSerializer extends StdSerializer<TareaEstudiante> {
    public TareaEstudianteSerializer() {
        this(null);
    }
    public TareaEstudianteSerializer(Class<TareaEstudiante> t) {
        super(t);
    }

    @Override
    public void serialize(TareaEstudiante tareaEstudiante, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", tareaEstudiante.getId());
        jgen.writeStringField("estudiante", tareaEstudiante.getEstudiante().getNombreCompleto());
        jgen.writeStringField("tarea",tareaEstudiante.getTarea().getRoute());

        jgen.writeStringField("fase",tareaEstudiante.getFase());
        jgen.writeNumberField("basico", tareaEstudiante.getPuntuacionBasica());
        jgen.writeNumberField("intermedio", tareaEstudiante.getPuntuacionIntermedia());
        jgen.writeNumberField("avanzado", tareaEstudiante.getPuntuacionAvanzada());
        jgen.writeEndObject();

    }
}
