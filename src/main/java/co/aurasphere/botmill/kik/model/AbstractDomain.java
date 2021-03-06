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
package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.incoming.event.TextMessageEvent;

/**
 * The Class AbstractDomain.
 * 
 * @author Alvin P. Reyes
 */
public abstract class AbstractDomain implements Domain {
	
	/** The action frames. */
	List<Frame> actionFrames;
	
	/**
	 * Instantiates a new abstract domain.
	 */
	public AbstractDomain() {
		this.actionFrames = new ArrayList<Frame>();
		this.buildDomain();
		this.sortContextBuckets();
	}
	
	/**
	 * Adds the action frame.
	 *
	 * @param actionFrame the action frame
	 */
	public void addActionFrame(Frame actionFrame) {
		if(actionFrame.getEvent() instanceof TextMessageEvent) {
			//	add to text message buckets.
		}
		this.actionFrames.add(actionFrame);
	}
	
	/**
	 * Sort context buckets.
	 */
	protected void sortContextBuckets(){
	}

	
}
