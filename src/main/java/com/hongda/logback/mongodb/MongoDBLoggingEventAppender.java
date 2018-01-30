package com.hongda.logback.mongodb;
/**
 * Copyright (C) 2016, The logback-mongodb developers. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */


import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import ch.qos.logback.classic.spi.ILoggingEvent;


/**
 * @author Christian Trutz
 * @author Tomasz Nurkiewicz
 * @since 0.1
 */
public class MongoDBLoggingEventAppender extends MongoDBAppenderBase<ILoggingEvent> {

	@Override
	protected BasicDBObject toMongoDocument(ILoggingEvent event) {
		String log = event.getFormattedMessage();
		BasicDBObject logEntry = new BasicDBObject();
		if (log.startsWith("{")) {
			try {
				logEntry.append("message", JSON.parse(log));
			} catch (Exception e) {
			}
		}
		logEntry.append("logger", event.getLoggerName());
		logEntry.append("thread", event.getThreadName());
		logEntry.append("timestamp", new Date(event.getTimeStamp()));
		logEntry.append("level", event.getLevel().toString());
		return logEntry;
	}

	@Override
	protected void append(ILoggingEvent event) {
		BasicDBObject basicDBObject = toMongoDocument(event);
		if(basicDBObject.containsField("message")) {
			super.append(event);
		}
		
	}
}
