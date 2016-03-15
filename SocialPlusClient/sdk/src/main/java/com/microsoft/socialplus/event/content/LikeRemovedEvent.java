/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.event.content;

import com.microsoft.socialplus.base.event.HandlingThread;
import com.microsoft.socialplus.base.event.ThreadType;
import com.microsoft.socialplus.data.model.LikeContentData;

/**
 * Result of remove like.
 */
@HandlingThread(ThreadType.MAIN)
public class LikeRemovedEvent extends LikeEvent {

	public LikeRemovedEvent(LikeContentData data, boolean result) {
		super(data, result);
	}
}
