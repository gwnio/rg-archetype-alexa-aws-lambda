package org.rg.archetype.lambda.alexa;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class SpeechletUtils {

	public static SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse) {
		return getSpeechletResponse(speechText, repromptText, isAskResponse, null);
	}

	public static SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse, Card card) {
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		if (isAskResponse) {
			PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
			repromptSpeech.setText(repromptText);
			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(repromptSpeech);

			if (card != null) {
				return SpeechletResponse.newAskResponse(speech, reprompt, card);
			} else {
				return SpeechletResponse.newAskResponse(speech, reprompt);
			}
		} else {
			if (card != null) {
				return SpeechletResponse.newTellResponse(speech, card);
			} else {
				return SpeechletResponse.newTellResponse(speech);
			}
		}
	}
	
	public static SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse, Card card, boolean ssml) {
		OutputSpeech speech;
		if (!ssml) {
			PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
			plainTextOutputSpeech.setText(speechText);
			speech = plainTextOutputSpeech;
		} else {
			SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
			ssmlOutputSpeech.setSsml(speechText);
			speech = ssmlOutputSpeech;
		}

		if (isAskResponse) {
			PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
			repromptSpeech.setText(repromptText);
			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(repromptSpeech);

			if (card != null) {
				return SpeechletResponse.newAskResponse(speech, reprompt, card);
			} else {
				return SpeechletResponse.newAskResponse(speech, reprompt);
			}
		} else {
			if (card != null) {
				return SpeechletResponse.newTellResponse(speech, card);
			} else {
				return SpeechletResponse.newTellResponse(speech);
			}
		}
	}
	
	public static SpeechletResponse getSpeechletResponse(ResponseData data) {
		return getSpeechletResponse(data.getSpeechText(), data.getRepromptText(), data.isAskResponse(), data.getCard(), data.isSsml());
	}
	
	public static SpeechletResponse getStopSpeechletResponse() {
		SpeechletResponse response = new SpeechletResponse();
		return response;
	}
}
