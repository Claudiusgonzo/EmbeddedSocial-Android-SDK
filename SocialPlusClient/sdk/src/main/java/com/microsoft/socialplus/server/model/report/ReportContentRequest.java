/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.server.model.report;

import com.microsoft.autorest.CommentReportsOperations;
import com.microsoft.autorest.CommentReportsOperationsImpl;
import com.microsoft.autorest.ReplyReportsOperations;
import com.microsoft.autorest.ReplyReportsOperationsImpl;
import com.microsoft.autorest.TopicReportsOperations;
import com.microsoft.autorest.TopicReportsOperationsImpl;
import com.microsoft.autorest.models.ContentType;
import com.microsoft.autorest.models.PostReportRequest;
import com.microsoft.autorest.models.Reason;
import com.microsoft.rest.ServiceException;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.socialplus.server.model.UserRequest;

import java.io.IOException;

import retrofit2.Response;

public class ReportContentRequest extends UserRequest {

	private static final TopicReportsOperations TOPIC_REPORT;
	private static final CommentReportsOperations COMMENT_REPORT;
	private static final ReplyReportsOperations REPLY_REPORT;

	static {
		TOPIC_REPORT = new TopicReportsOperationsImpl(RETROFIT, CLIENT);
		COMMENT_REPORT = new CommentReportsOperationsImpl(RETROFIT, CLIENT);
		REPLY_REPORT = new ReplyReportsOperationsImpl(RETROFIT, CLIENT);
	}

	private PostReportRequest request;
	private String contentHandle;
	private ContentType contentType;

	public ReportContentRequest(ContentType contentType, String contentHandle, Reason reason) {
		if (contentType == ContentType.UNKNOWN) {
			throw new IllegalArgumentException("Content type cannot be unknown");
		}
		request = new PostReportRequest();
		request.setReason(reason);
		this.contentType = contentType;
		this.contentHandle = contentHandle;
	}

	@Override
	public Response send() throws ServiceException, IOException {
		ServiceResponse<Object> serviceResponse;
		switch (contentType) {
			case TOPIC:
				serviceResponse = TOPIC_REPORT.postReport(contentHandle, request, bearerToken);
				break;
			case COMMENT:
				serviceResponse = COMMENT_REPORT.postReport(contentHandle, request, bearerToken);
				break;
			case REPLY:
				serviceResponse = REPLY_REPORT.postReport(contentHandle, request, bearerToken);
				break;
			default:
				throw new IllegalStateException("Unknown type for like");
		}
		return serviceResponse.getResponse();
	}
}
