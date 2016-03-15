/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.server.model.content.topics;

import com.microsoft.socialplus.server.model.view.TopicView;

public class GetTopicResponse {

	private TopicView topic;

	public GetTopicResponse(TopicView topic) {
		this.topic = topic;
	}

	public TopicView getTopic() {
		return topic;
	}
}
