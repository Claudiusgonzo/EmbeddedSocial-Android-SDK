/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.ui.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.microsoft.socialplus.server.model.view.ActivityView;
import com.microsoft.socialplus.service.IntentExtras;
import com.microsoft.socialplus.ui.activity.DisplayNoteActivity;
import com.microsoft.socialplus.ui.activity.TopicActivity;

/**
 * OnClickListener for activity feed items.
 */
public class OnActivityItemClickListener implements View.OnClickListener {

	private final ActivityView activity;

	public OnActivityItemClickListener(ActivityView activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		Context context = v.getContext();
		switch (activity.getActivityType()) {
			case FOLLOWING:
			case FOLLOW_REQUEST:
			case FOLLOW_ACCEPT:
				openUserProfile(context);
				break;
			case LIKE:
			case CHILD:
			case CHILD_PEER:
			case MENTION:
				openContent(context);
				break;
		}

	}

	protected void openContent(Context context) {
		String handle = activity.getActedOnContentHandle();
		switch (activity.getActedOnContentType()) {
			case TOPIC:
				openTopic(context, handle);
				break;
			case COMMENT:
				openComment(context, handle);
				break;
			case REPLY:
				openReply(context, handle);
				break;
		}
	}

	private void openUserProfile(Context context) {
		ProfileOpenHelper.openUserProfile(context, activity.getActorUsers().get(0));
	}

	private void openTopic(Context context, String handle) {
		Intent intent = new Intent(context, TopicActivity.class);
		intent.putExtra(IntentExtras.TOPIC_HANDLE, handle);
		context.startActivity(intent);
	}

	private void openComment(Context context, String handle) {
		Intent intent = new Intent(context, DisplayNoteActivity.class);
		intent.putExtra(IntentExtras.COMMENT_HANDLE, handle);
		context.startActivity(intent);
	}

	private void openReply(Context context, String handle) {
		Intent intent = new Intent(context, DisplayNoteActivity.class);
		intent.putExtra(IntentExtras.REPLY_HANDLE, handle);
		context.startActivity(intent);
	}
}
