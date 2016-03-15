/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.event.click;

import com.microsoft.socialplus.base.event.AbstractEvent;
import com.microsoft.socialplus.base.event.HandlingThread;
import com.microsoft.socialplus.base.event.ThreadType;

/**
 * On navigation item clicked event.
 */
@HandlingThread(ThreadType.CALLING_MAIN)
public class NavigationItemClickedEvent extends AbstractEvent {
}
