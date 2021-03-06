/**
 * 
 * MIT License
 *
 * Copyright (c) 2017 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package co.aurasphere.botmill.kik.incoming.event;

import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.model.Event;

/**
 * The Class TextMessageEvent.
 * 
 * @author Alvin P. Reyes
 */
public class TextMessageEvent implements Event {
	
	/** The keyword text. */
	private String keywordText;

	/**
	 * Instantiates a new text message event.
	 */
	public TextMessageEvent() {}
	
	/**
	 * Sets the text.
	 *
	 * @param text the text
	 * @return the text message event
	 */
	public TextMessageEvent setText(String text) {
		this.keywordText = text;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Event#verifyEvent(co.aurasphere.botmill.kik.incoming.model.IncomingMessage)
	 */
	@Override
	public boolean verifyEvent(IncomingMessage message) {
		if (message instanceof TextMessage) {
			if(this.keywordText.toLowerCase().equals(message.getBody().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
