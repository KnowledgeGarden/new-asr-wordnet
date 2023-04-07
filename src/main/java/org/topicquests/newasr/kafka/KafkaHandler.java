/*
 * Copyright 2019, 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr.kafka;

import org.topicquests.backside.kafka.consumer.StringConsumer;
import org.topicquests.backside.kafka.consumer.api.IMessageConsumerListener;
import org.topicquests.newasr.ASRWordnetEnvironment;
import org.topicquests.support.api.IEnvironment;


/**
 * @author jackpark
 * 
 */
public class KafkaHandler {
	private ASRWordnetEnvironment environment;
	private StringConsumer consumer;
	private final boolean isRewind;
	private final int pollSeconds = 2;
	private final String
		CONSUMER_TOPIC,
		AGENT_GROUP; //"BiomedSentenceAgent";

	/**
	 * 
	 */
	public KafkaHandler(ASRWordnetEnvironment env, IMessageConsumerListener listener, String cTopic, String agentGroup) {
		environment = env;
		String rw = environment.getStringProperty("ConsumerRewind");
		isRewind = rw.equalsIgnoreCase("T");
		CONSUMER_TOPIC = cTopic;
		AGENT_GROUP = agentGroup;
		consumer = new StringConsumer((IEnvironment)environment, AGENT_GROUP,
					CONSUMER_TOPIC, listener, isRewind, pollSeconds);
	}
		
	
	public void shutDown() {
		consumer.close();
	}

}
