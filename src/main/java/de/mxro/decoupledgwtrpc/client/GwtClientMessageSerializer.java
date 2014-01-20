package de.mxro.decoupledgwtrpc.client;

import java.io.Serializable;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class GwtClientMessageSerializer {

	Object serviceInstance;
	
	public String serializeForServer(Serializable serializable) {
		try {
			SerializationStreamWriter writer = ((SerializationStreamFactory) serviceInstance).createStreamWriter();

			writer.writeObject(serializable);

			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * An instance of the RemoteService for which messages are to be generated.
	 * @param serviceInstance
	 */
	public GwtClientMessageSerializer(Object serviceInstance) {
		super();
		this.serviceInstance = serviceInstance;
	}
	
	
	
}
