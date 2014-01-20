package de.mxro.decoupledgwtrpc.client;

import java.io.Serializable;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.seanchenxi.gwt.storage.client.serializer.ServerStorageRPCSerializerImpl;

public class GwtClientSerializer {

	private final Class<? extends RemoteService> baseService;
	private final ServerStorageRPCSerializerImpl clientSerializer;

	public String serializeForServer(Serializable serializable) {
		try {
			SerializationStreamWriter writer = ((SerializationStreamFactory) GWT
					.create(baseService)).createStreamWriter();

			writer.writeObject(serializable);

			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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

	public GwtClientSerializer(Class<? extends RemoteService> baseService) {
		super();
		this.baseService = baseService;
		this.clientSerializer = new ServerStorageRPCSerializerImpl();
	}

	public GwtClientSerializer(Class<? extends RemoteService> baseService,
			ServerStorageRPCSerializerImpl serializer) {
		super();
		this.baseService = baseService;
		this.clientSerializer = serializer;
	}

}
