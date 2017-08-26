package org.rg.archetype.aws.lambda.apiai;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import ai.api.GsonFactory;
import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import ai.api.model.Result;
import ai.api.util.IOUtils;

public class MainRequestStreamHandler implements RequestStreamHandler {

	private LambdaLogger logger;
	
	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		logger = context.getLogger();

		Fulfillment output = new Fulfillment();

		Gson gson = GsonFactory.getDefaultFactory().getGson();
		String inputJson = IOUtils.readAll(inputStream);
		logger.log(inputJson);
		AIResponse response = gson.fromJson(inputJson, AIResponse.class);

		Result result = response.getResult();
		String intentName = result.getMetadata().getIntentName();

		if (intentName.equals(AIConsts.IntentName.TodaysDate)) {
			String speech = "Today's date is " + new SimpleDateFormat("M, d y").format(new Date()) + ".";
			output.setSpeech(speech);
		} else if (intentName.equals(AIConsts.IntentName.RequestFavoriteNumber)) {
			handleFavoriteNumber(response.getResult(), output);
		}

		IOUtils.writeAll(gson.toJson(output), outputStream);
	}

	private void handleFavoriteNumber(Result result, Fulfillment output) {
		JsonElement favoriteNumberJe = result.getParameters().get(AIConsts.ParamName.FavoriteNumber);

		int favoriteNumber = favoriteNumberJe.getAsInt();
		
		String speech = "I see your favorite number is " + favoriteNumber + ".";
		
		output.setSpeech(speech);
	}
}
