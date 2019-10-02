package be.jkin.kudosusuariosrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;

import java.io.Serializable;


public final class KudosMessage implements Serializable {
    private final String IdKudos;
    private final String Fuente;
    private final String Destino;
    private final String Accion;

    public KudosMessage(@JsonProperty("id") String idKudos,
                        @JsonProperty("fuente") String fuente,
                        @JsonProperty("destino") String destino,
                        @JsonProperty("accion") String accion)
    {
        this.IdKudos = idKudos;
        this.Fuente = fuente;
        this.Destino = destino;
        this.Accion = accion;
    }

    public KudosMessage(JsonObject jsonKudos)
    {
        this.IdKudos = jsonKudos.get("IdKudos").getAsString();
        this.Fuente = jsonKudos.get("Fuente").getAsString();
        this.Destino = jsonKudos.get("Destino").getAsString();
        this.Accion = jsonKudos.get("Accion").getAsString();
    }

    public String getIdKudos() {
        return IdKudos;
    }

    public String getFuente() {
        return Fuente;
    }

    public String getDestino() {
        return Destino;
    }

    public String getAccion() {
        return Accion;
    }

    @Override
    public String toString()
    {
        return "KudosMessage{"+
                "id= '" + IdKudos +"'"+
                "fuente = '"+ Fuente  +"'"+
                "destino = '"+ Destino +"'"+
                "accion = '"+ Accion+"'";
    }
}
