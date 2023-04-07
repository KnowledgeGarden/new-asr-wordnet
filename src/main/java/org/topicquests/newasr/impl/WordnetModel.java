/**
 * 
 */
package org.topicquests.newasr.impl;

import org.topicquests.newasr.ASRWordnetEnvironment;
import org.topicquests.newasr.api.IWordnetModel;

import com.google.gson.JsonObject;

/**
 * @author jackpark
 *
 */
public class WordnetModel implements IWordnetModel {
	private ASRWordnetEnvironment environment;

	/**
	 * 
	 */
	public WordnetModel(ASRWordnetEnvironment env) {
		environment = env;
	}

	@Override
	public boolean acceptDocument(JsonObject data) {
		// TODO Auto-generated method stub
		return false;
	}

}
