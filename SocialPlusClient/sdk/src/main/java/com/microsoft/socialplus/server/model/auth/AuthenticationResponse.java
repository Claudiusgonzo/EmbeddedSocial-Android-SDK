/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.server.model.auth;

import com.microsoft.socialplus.autorest.models.PostSessionResponse;
import com.microsoft.socialplus.autorest.models.PostUserResponse;

public class AuthenticationResponse {

	private String userHandle;
	private String sessionToken;
	private int messageId;

	public AuthenticationResponse(PostUserResponse response) {
		this.userHandle = response.getUserHandle();
		this.sessionToken = response.getSessionToken();
	}

	public AuthenticationResponse(PostSessionResponse response, int messageId) {
		this.userHandle = response.getUserHandle();
		this.sessionToken = response.getSessionToken();
		this.messageId = messageId;
	}

	public String getUserHandle() {
		return userHandle;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public int getMessageId() {
		return messageId;
	}
}
