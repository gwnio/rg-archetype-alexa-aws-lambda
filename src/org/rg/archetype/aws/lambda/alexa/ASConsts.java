package org.rg.archetype.aws.lambda.alexa;

public class ASConsts {

	public static final String UtteranceSpeechNotUnderstood = "Sorry I didn't understand what I heard.";
	
	public static interface IntentName {
		static final String AmazonNoIntent = "AMAZON.NoIntent";
		static final String AmazonYesIntent = "AMAZON.YesIntent";
		static final String AmazonStopIntent = "AMAZON.StopIntent";
		static final String AmazonCancelIntent = "AMAZON.CancelIntent";
		
		static final String Goodbye = "Goodbye";
		static final String Help = "Help";
		static final String TodaysDate = "TodaysDate";
	}
}
