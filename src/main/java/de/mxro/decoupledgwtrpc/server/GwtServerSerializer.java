package de.mxro.decoupledgwtrpc.server;

import com.google.gwt.user.client.rpc.impl.AbstractSerializationStream;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamWriter;

public class GwtServerSerializer {

	private final SerializationPolicy policy;
	
	/**
	 * Needs message in format as described here
	 * https://docs.google.com/document/d/1eG0YocsYYbNAtivkLtcaiEE5IOF5u4LUol8-LL0TIKU/edit
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T deserializeMessageFromClient(String data) {
		try {

			String content = data;

			ServerSerializationStreamReader streamReader = new ServerSerializationStreamReader(
					Thread.currentThread().getContextClassLoader(), new SerializationPolicyProvider() {
						
						@Override
						public SerializationPolicy getSerializationPolicy(String moduleBaseURL,
								String serializationPolicyStrongName) {
							return policy;
						}
					});

			streamReader.setFlags(AbstractSerializationStream.DEFAULT_FLAGS);
			streamReader.prepareToRead(content);

			return (T) streamReader.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String serializeObjectForClient(Object obj) {
		try {
			
			ServerSerializationStreamWriter stream = new ServerSerializationStreamWriter(
					policy);
			stream.setFlags(AbstractSerializationStream.DEFAULT_FLAGS);

			stream.prepareToWrite();

			stream.serializeValue(obj, Object.class);

			return stream.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Serializes an object for a GWT client as part of an RPC message.
	 * 
	 * @param obj
	 * @return
	 */
	public String serializeMessageForClient(Object obj) {
		
	}
	
	public GwtServerSerializer(SerializationPolicy policy) {
		super();
		this.policy = policy;
	}

	
	
}
