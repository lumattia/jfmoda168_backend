package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.model.entity.TareaEstudiante;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        jgen.writeStringField("estudiante", tareaEstudiante.getId().getEstudiante().getNombreCompleto());
        jgen.writeStringField("tarea",tareaEstudiante.getId().getTarea().getRoute());
        Map<Byte,String> nivel=new HashMap<>();
        nivel.put((byte) 1, "Facil");
        nivel.put((byte) 2, "Intermedio");
        nivel.put((byte) 3, "Difícil");
        jgen.writeStringField("fase",nivel.get(tareaEstudiante.getFase()));
        jgen.writeNumberField("basico", tareaEstudiante.getPuntuacionBasica());
        jgen.writeNumberField("intermedio", tareaEstudiante.getPuntuacionIntermedia());
        jgen.writeNumberField("avanzado", tareaEstudiante.getPuntuacionAvanzada());
        jgen.writeEndObject();

    }
}
