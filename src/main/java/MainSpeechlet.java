package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;

/**
 * Helpful:
 * https://github.com/amzn/alexa-skills-kit-java/tree/master/samples/src/main/java/helloworld
 */
public class MainSpeechlet implements Speechlet {

	private static final Logger log = LoggerFactory.getLogger(MainSpeechlet.class);

	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
		log.debug("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		// any initialization logic goes here
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		log.debug("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		return getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
		log.debug("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		// Get intent from the request object.
		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;
		log.debug("Intent: " + intentName);

		IntentResponseMap responseMap = new IntentResponseMap();

		if (intentName.equals(Consts.IntentName.AmazonNoIntent)) {
			return SpeechletUtils.getStopSpeechletResponse();
		} else if (intentName.equals(Consts.IntentName.AmazonYesIntent)) {
			return SpeechletUtils.getSpeechletResponse(responseMap.get(Consts.IntentName.Help));
		} else if (intentName.equals(Consts.IntentName.AmazonStopIntent)) {
			return SpeechletUtils.getStopSpeechletResponse();
		}
		if (intentName.equals(Consts.IntentName.AmazonCancelIntent)) {
			return SpeechletUtils.getStopSpeechletResponse();
		} else {
			ResponseData data = responseMap.get(intentName);
			if (data != null) {
				return SpeechletUtils.getSpeechletResponse(responseMap.get(intentName));
			} else {
				return SpeechletUtils.getSpeechletResponse(Consts.UtteranceSpeechNotUnderstood, null, false);
			}
		}
	}

	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
		log.debug("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		// any cleanup logic goes here
	}

	private SpeechletResponse getWelcomeResponse() {
		String speechText = "Hi. This is a basic Alexa skill using AWS as a endpoint.";

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}
}
