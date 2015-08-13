package de.mxro.decoupledgwtrpc.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.SerializationException;
import com.seanchenxi.gwt.storage.client.serializer.ServerStorageRPCSerializerImpl;

public class GwtClientSerializer {

    private final ServerStorageRPCSerializerImpl clientSerializer;

    public String serializeForClient(final Serializable obj) {
        try {
            return this.clientSerializer.serialize(Serializable.class, obj);
        } catch (final SerializationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Does not accept string serialized for the server.
     * 
     * @param data
     * @return
     */
    public Object deserialize(final String data) {
        if (data == null) {
            throw new NullPointerException("String to be deserialized must not be null.");
        }
        try {
            return this.clientSerializer.deserialize(Serializable.class, data);
        } catch (final SerializationException e) {
            throw new RuntimeException(e);
        }
    }

    public GwtClientSerializer() {
        super();
        this.clientSerializer = new ServerStorageRPCSerializerImpl();
    }

    public GwtClientSerializer(final ServerStorageRPCSerializerImpl serializer) {
        super();
        this.clientSerializer = serializer;
    }

}
