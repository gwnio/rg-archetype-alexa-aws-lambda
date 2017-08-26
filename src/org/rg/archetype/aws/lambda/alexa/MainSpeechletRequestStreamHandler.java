package org.rg.archetype.aws.lambda.alexa;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class MainSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

	private static final Set<String> supportedApplicationIds = new HashSet<String>();
	static {
		supportedApplicationIds.add("{your_alexa_skill_id_here}"); // i.e. format amzn1.ask.skill.{alphanumeric id here}
	}

	public MainSpeechletRequestStreamHandler() {
		super(new MainSpeechlet(), supportedApplicationIds);
	}
}
