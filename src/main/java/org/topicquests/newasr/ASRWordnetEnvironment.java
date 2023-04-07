/*
 * Copyright 2023 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.newasr;

import java.util.Map;

import org.topicquests.backside.kafka.consumer.api.IMessageConsumerListener;
import org.topicquests.newasr.api.IKafkaDispatcher;
import org.topicquests.newasr.api.IWordnetModel;
import org.topicquests.newasr.impl.ASRBaseEnvironment;
import org.topicquests.newasr.impl.DocumentListener;
import org.topicquests.newasr.impl.WordnetModel;
import org.topicquests.newasr.kafka.KafkaHandler;
import org.topicquests.support.config.Configurator;

/**
 * @author jackpark
 *
 */
public class ASRWordnetEnvironment extends ASRBaseEnvironment {
	private KafkaHandler documentConsumer;
	private Map<String,Object>kafkaProps;
	private IKafkaDispatcher documentListener;
	private IWordnetModel model = null;
	

	/**
	 * @param configPath
	 * @param logConfigPath
	 */
	public ASRWordnetEnvironment() {
		super("asr-wordnet-config.xml");
		kafkaProps = Configurator.getProperties("kafka-topics.xml");
		model = new WordnetModel(this);
		documentListener = new DocumentListener(this);
		String cTopic = (String)kafkaProps.get("DocumentConsumerTopic");
		documentConsumer = new KafkaHandler(this, (IMessageConsumerListener)documentListener, cTopic, AGENT_GROUP);
		
		// shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread()
	    {
	      public void run()
	      {
	        shutDown();
	      }
	    });

	}

	public IWordnetModel getModel() {
		return model;
	}
	@Override
	public void shutDown() {
		System.out.println("Shutting down");
		documentConsumer.shutDown();	
	}

}
