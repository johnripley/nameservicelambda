package org.johnripley.nameservicelambda;

import java.io.IOException;

import org.johnripley.aws.lambda.AbstractRequestStreamHandler;
import org.johnripley.nameinfo.BaseNameInfo;
import org.johnripley.nameinfo.BaseNameLookup;
import org.johnripley.nameinfo.NameLookup;
import org.johnripley.nameinfo.NameStats;
import org.json.JSONObject;

public class NameServiceStatsHandler extends AbstractRequestStreamHandler {

	private static NameLookup nameInfo = new BaseNameLookup();

	@Override
	protected void doProcessRequest(JSONObject request, JSONObject response) throws IOException {
		NameStats ns = nameInfo.getStatistic(new BaseNameInfo(request.optString("name", ""), request.optInt("year", 0)));
		response.put("name", ns.getName());
		response.put("year", ns.getYear());
		response.put("countMale", ns.getCountMale());
		response.put("countFemale", ns.getCountFemale());
		response.put("totalMale", ns.getTotalMale());
		response.put("toalFemale", ns.getTotalFemale());
	}

}
