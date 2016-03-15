/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.event.content;

import com.microsoft.socialplus.base.event.AbstractEvent;
import com.microsoft.socialplus.base.event.HandlingThread;
import com.microsoft.socialplus.base.event.ThreadType;
import com.microsoft.socialplus.data.model.DiscussionItem;

/**
 * Base class to add comments and replies.
 */
@HandlingThread(ThreadType.MAIN)
abstract class NoteAddedEvent extends AbstractEvent {
	private final DiscussionItem data;
	private final String handle;
	private final boolean result;

	public NoteAddedEvent(DiscussionItem data, String handle, boolean result) {
		this.data = data;
		this.handle = handle;
		this.result = result;
	}

	public DiscussionItem getData() {
		return data;
	}

	public String getHandle() {
		return handle;
	}

	public boolean isResult() {
		return result;
	}
}
