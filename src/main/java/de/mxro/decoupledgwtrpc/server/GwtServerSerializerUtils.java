package de.mxro.decoupledgwtrpc.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

public class GwtServerSerializerUtils {

	public static SerializationPolicy getSerializationPolicy(InputStream stream) {
		List<ClassNotFoundException> classNotFoundExceptions = new ArrayList<ClassNotFoundException>();
		SerializationPolicy policy;
		try {
			policy = SerializationPolicyLoader.loadFromStream(
					stream, classNotFoundExceptions);
			return policy;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SerializationPolicy getSerializationPolicy(File path) {
		

		File policyFile = getSerializationPolicyFile(path);

		try {
			return getSerializationPolicy(new FileInputStream(policyFile));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	public static File getSerializationPolicyFile(File path) {
		File folder = new File("src/main/webapp/serializationclient");

		File policyFile = null;
		for (File f : folder.listFiles()) {

			if (f.getName().contains(".gwt.rpc")) {
				policyFile = f;
			}
		}
		return policyFile;
	}
	
	public static Method getServiceMethod(Class<? extends RemoteService> service, String methodName) {
		try {
			return service.getDeclaredMethod(methodName, Serializable.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
