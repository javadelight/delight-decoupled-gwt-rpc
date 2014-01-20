package de.mxro.decoupledgwtrpc.server;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

public class GwtServerSerializerUtils {

	public static SerializationPolicy getSerializationPolicy(File path) {
		List<ClassNotFoundException> classNotFoundExceptions = new ArrayList<ClassNotFoundException>();

		File policyFile = getSerializationPolicyFile(path);

		SerializationPolicy policy;
		try {
			policy = SerializationPolicyLoader.loadFromStream(
					new FileInputStream(policyFile), classNotFoundExceptions);
			return policy;
		} catch (Exception e) {
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
	
}
