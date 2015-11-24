package de.mxro.decoupledgwtrpc.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class GwtClientMessageSerializer {

    Object serviceInstance;

    public String serializeForServer(final Serializable serializable) {
        try {
            final SerializationStreamWriter writer = ((SerializationStreamFactory) serviceInstance)
                    .createStreamWriter();

            /*
             * Console.log("serialize " + serializable.getClass());
             * 
             * if (serializable instanceof ArrayList) {
             * 
             * final ArrayList list = (ArrayList) serializable; for (final
             * Object o : list) { Console.log(" -> " + o.getClass());
             * Console.log("     -> " + o.toString());
             * 
             * } }
             */

            writer.writeObject(serializable);

            return writer.toString();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * An instance of the RemoteService for which messages are to be generated.
     * 
     * @param serviceInstance
     */
    public GwtClientMessageSerializer(final Object serviceInstance) {
        super();
        this.serviceInstance = serviceInstance;
    }

}
