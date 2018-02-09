package org.johnripley.nameservicelambda;

import org.johnripley.nameinfo.BaseNameLookup;
import org.johnripley.nameinfo.GenderInfo;
import org.johnripley.nameinfo.NameInfo;
import org.johnripley.nameinfo.NameLookup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class NameServiceGenderHandler implements RequestHandler<NameInfo, GenderInfo> {

	private static NameLookup nameInfo = new BaseNameLookup();

	@Override
	public GenderInfo handleRequest(NameInfo input, Context context) {
		context.getLogger().log("Input: " + input);
		return nameInfo.getGender(input);
	}

}