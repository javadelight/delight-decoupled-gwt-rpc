package de.mxro.decoupledgwtrpc.client;

import java.io.Serializable;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class GwtClientSerializer {

	private final Class<? extends RemoteService> baseService;
	
	public String serializeForServer(Serializable serializable)
			throws SerializationException {
		
		SerializationStreamWriter writer = ((SerializationStreamFactory) GWT
				.create(baseService)).createStreamWriter();
		
		writer.writeObject(serializable);
		
		return writer.toString();
	}

	public GwtClientSerializer(Class<? extends RemoteService> baseService) {
		super();
		this.baseService = baseService;
	}

	
	
	
}
