package org.rg.archetype.lambda.alexa;

import com.amazon.speech.ui.Card;

public class ResponseData {

	private String speechText;
	
	private String repromptText;
	
	private boolean isAskResponse;
	
	private Card card;
	
	private boolean ssml;

	public ResponseData() {
	}
	
	public ResponseData(String speechText) {
		this(speechText, null, false, null, false);
	}
	
	public ResponseData(String speechText, boolean isAskResponse) {
		this(speechText, null, isAskResponse, null, false);
	}
	
	public ResponseData(String speechText, String repromptText, boolean isAskResponse, Card card, boolean ssml) {
		this.speechText = speechText;
		this.repromptText = repromptText;
		this.isAskResponse = isAskResponse;
		this.card = card;
		this.ssml = ssml;
	}
	
	public String getSpeechText() {
		return speechText;
	}

	public void setSpeechText(String speechText) {
		this.speechText = speechText;
	}

	public String getRepromptText() {
		return repromptText;
	}

	public void setRepromptText(String repromptText) {
		this.repromptText = repromptText;
	}

	public boolean isAskResponse() {
		return isAskResponse;
	}

	public void setAskResponse(boolean isAskResponse) {
		this.isAskResponse = isAskResponse;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isSsml() {
		return ssml;
	}

	public void setSsml(boolean ssml) {
		this.ssml = ssml;
	}
}
