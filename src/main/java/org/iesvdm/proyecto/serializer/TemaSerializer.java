package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.model.entity.Tema;

import java.io.IOException;

public class TemaSerializer extends StdSerializer<Tema> {
    public TemaSerializer() {
        this(null);
    }
    public TemaSerializer(Class<Tema> t) {
        super(t);
    }

    @Override
    public void serialize(Tema tema, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", tema.getId());
        jgen.writeStringField("nombre", tema.getNombre());
        if (tema.getTareas()!=null){
            jgen.writeFieldName("tareas");
            jgen.writeObject(tema.getTareas());
        }
        if (tema.getAula()!=null){
            jgen.writeFieldName("aulas");
            jgen.writeObject(tema.getAula().getId());
        }
        jgen.writeEndObject();

    }
}
