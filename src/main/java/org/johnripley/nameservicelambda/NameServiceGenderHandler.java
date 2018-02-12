package org.johnripley.nameservicelambda;

import java.io.IOException;

import org.johnripley.aws.lambda.AbstractRequestStreamHandler;
import org.johnripley.nameinfo.BaseNameInfo;
import org.johnripley.nameinfo.BaseNameLookup;
import org.johnripley.nameinfo.GenderInfo;
import org.johnripley.nameinfo.NameLookup;
import org.json.JSONObject;

public class NameServiceGenderHandler extends AbstractRequestStreamHandler {

	private static NameLookup nameInfo = new BaseNameLookup();

	@Override
	protected void doProcessRequest(JSONObject request, JSONObject response) throws IOException {
		GenderInfo gi = nameInfo.getGender(new BaseNameInfo(request.optString("name", ""), request.optInt("", 0)));
		response.put("name", gi.getName());
		response.put("gender", gi.getGender());
		response.put("confidence", gi.getConfidence());
	}

}
