/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.fetcher;

import com.microsoft.socialplus.fetcher.base.DataState;
import com.microsoft.socialplus.fetcher.base.Fetcher;
import com.microsoft.socialplus.fetcher.base.RequestType;
import com.microsoft.socialplus.server.model.BaseRequest;

import java.util.List;

/**
 * Implementation of {@link Fetcher} delegating fetching data to {@link DataRequestExecutor}.
 *
 * @param <T> type of items
 * @param <R> type of server request
 */
class ServerDataFetcher<T, R extends BaseRequest> extends Fetcher<T> {

	private final DataRequestExecutor<T, R> requestExecutor;

	ServerDataFetcher(DataRequestExecutor<T, R> requestExecutor) {
		this.requestExecutor = requestExecutor;
	}

	@Override
	protected List<T> fetchDataPage(DataState dataState, RequestType requestType, int pageSize) throws Exception {
		return requestExecutor.fetchData(dataState, requestType, pageSize);
	}

}
