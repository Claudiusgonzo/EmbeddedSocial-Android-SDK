/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.event.data;

import com.microsoft.socialplus.base.event.AbstractEvent;
import com.microsoft.socialplus.data.model.SearchType;

/**
 * Search text changed event.
 */
public class SearchTextChangedEvent extends AbstractEvent {

	private final SearchType searchType;
	private final String text;

	public SearchTextChangedEvent(SearchType searchType, String text) {
		this.searchType = searchType;
		this.text = text;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public String getText() {
		return text;
	}
}
