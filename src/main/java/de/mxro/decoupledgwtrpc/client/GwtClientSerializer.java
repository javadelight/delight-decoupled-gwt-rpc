package de.mxro.decoupledgwtrpc.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.seanchenxi.gwt.storage.client.serializer.ServerStorageRPCSerializerImpl;

public class GwtClientSerializer {

	private final ServerStorageRPCSerializerImpl clientSerializer;

	

	public String serializeForClient(Serializable obj) {
		try {
			return this.clientSerializer.serialize(Serializable.class, obj);
		} catch (SerializationException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Does not accept string serialized for the server.
	 * 
	 * @param data
	 * @return
	 */
	public String deserialize(String data) {
		try {
			return this.clientSerializer.deserialize(Serializable.class, data);
		} catch (SerializationException e) {
			throw new RuntimeException(e);
		}
	}

	public GwtClientSerializer() {
		super();
		this.clientSerializer = new ServerStorageRPCSerializerImpl();
	}

	public GwtClientSerializer(
			ServerStorageRPCSerializerImpl serializer) {
		super();
		this.clientSerializer = serializer;
	}

}
