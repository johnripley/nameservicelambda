package org.johnripley.nameservicelambda;

import org.johnripley.nameinfo.BaseNameInfo;
import org.johnripley.nameinfo.BaseNameLookup;
import org.johnripley.nameinfo.GenderInfo;
import org.johnripley.nameinfo.NameLookup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class NameServiceGenderHandler implements RequestHandler<BaseNameInfo, GenderInfo> {

	//this static instance will global to the AWS lambda container while the container is warm (~15mins) 
	private static NameLookup nameInfo = new BaseNameLookup();

	@Override
	public GenderInfo handleRequest(BaseNameInfo input, Context context) {
		context.getLogger().log("Input: " + input);
		return nameInfo.getGender(input);
	}

}
