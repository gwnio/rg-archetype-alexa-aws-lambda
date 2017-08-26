package org.rg.archetype.aws.lambda.alexa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IntentResponseMap extends HashMap<String, ResponseData> implements Map<String, ResponseData> {

	private static final long serialVersionUID = 8100031730460925706L;

	public IntentResponseMap() {
		String followUpSpeech = " Is there anything else I can help you with?";
		
		put(ASConsts.IntentName.Goodbye, new ResponseData("Goodbye!"));
		put(ASConsts.IntentName.Help, new ResponseData("I'm a very simple skill, I can tell you what today's date is.", true));
		put(ASConsts.IntentName.TodaysDate, new ResponseData("Today's date is " + new SimpleDateFormat("M, d y").format(new Date()) + "." + followUpSpeech, true));
	}
}
