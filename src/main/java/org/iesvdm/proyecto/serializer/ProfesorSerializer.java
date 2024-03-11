package org.iesvdm.proyecto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.proyecto.domain.Profesor;

import java.io.IOException;

public class ProfesorSerializer extends StdSerializer<Profesor> {
    public ProfesorSerializer() {
        this(null);
    }
    public ProfesorSerializer(Class<Profesor> t) {
        super(t);
    }

    @Override
    public void serialize(Profesor profesor, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", profesor.getId());
        jgen.writeStringField("nombre", profesor.getNombre());
        jgen.writeStringField("apellido1", profesor.getApellido1());
        jgen.writeStringField("apellido2", profesor.getApellido2());
        if (profesor.getClases()!=null){
            jgen.writeFieldName("clase");
            jgen.writeObject(profesor.getClases().stream().map(clase -> clase.getCurso().getNombre()+" "+clase.getAsignatura().getNombre()).toList());
        }
        jgen.writeEndObject();

    }
}
