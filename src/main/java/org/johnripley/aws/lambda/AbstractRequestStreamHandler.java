package org.johnripley.aws.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.IOUtils;

public abstract class AbstractRequestStreamHandler implements RequestStreamHandler {

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		JSONObject input = new JSONObject(IOUtils.toString(inputStream));
		boolean proxy = input.has("queryStringParameters");
		String response = processResponse(proxy, processRequest(proxy, input));
		context.getLogger().log("\nInput:\n" + input);
		context.getLogger().log("\nOutput:\n" + response);
		try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
			writer.write(response);
		}
	}

	protected String processResponse(boolean proxy, JSONObject response) throws IOException {
		if (proxy) {
			JSONObject proxyResponse = new JSONObject();
			proxyResponse.put("body", response.toString());
			proxyResponse.put("isBase64Encoded", false);
			proxyResponse.put("headers", new JSONObject());
			proxyResponse.put("statusCode", 200);
			return proxyResponse.toString();
		} else
			return response.toString();
	}

	protected JSONObject processRequest(boolean proxy, JSONObject request) throws IOException {
		if (proxy)
			request = request.getJSONObject("queryStringParameters");
		JSONObject response = new JSONObject();
		doProcessRequest(request, response);
		return response;
	}

	abstract protected void doProcessRequest(JSONObject request, JSONObject response) throws IOException;

}
