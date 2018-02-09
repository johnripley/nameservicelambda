package org.johnripley.nameservicelambda;

import org.johnripley.nameinfo.BaseNameLookup;
import org.johnripley.nameinfo.NameInfo;
import org.johnripley.nameinfo.NameLookup;
import org.johnripley.nameinfo.NameStats;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class NameServiceStatsHandler implements RequestHandler<NameInfo, NameStats> {

	private static NameLookup nameInfo = new BaseNameLookup();

	@Override
	public NameStats handleRequest(NameInfo input, Context context) {
		context.getLogger().log("Input: " + input);
		return nameInfo.getStatistic(input);
	}

}
