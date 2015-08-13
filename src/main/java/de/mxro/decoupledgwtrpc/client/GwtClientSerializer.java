package de.mxro.decoupledgwtrpc.client;

import delight.gwt.console.Console;

import java.io.Serializable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamReader;
import com.google.gwt.user.client.rpc.impl.Serializer;
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

    private final static Serializer TYPE_SERIALIZER = GWT.create(Serializer.class);

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
            final String serializedString = data;
            if (serializedString == null) {
                return null;
            } else if (String.class.equals(Serializable.class)) {
                return serializedString;
            }

            final ClientSerializationStreamReader reader = new ClientSerializationStreamReader(TYPE_SERIALIZER);
            Console.log("prepare to read " + data);
            reader.prepareToRead(serializedString);
            Console.log("reading it");
            // Object obj =
            // TYPE_SERIALIZER.get(Serializable.class).read(reader);
            return reader.readObject();

            // return this.clientSerializer.deserialize(Serializable.class,
            // data);
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
