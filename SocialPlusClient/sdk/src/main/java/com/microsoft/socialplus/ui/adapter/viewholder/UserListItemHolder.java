/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.socialplus.base.utils.ViewUtils;
import com.microsoft.socialplus.image.ImageViewContentLoader;
import com.microsoft.socialplus.image.UserPhotoLoader;
import com.microsoft.socialplus.sdk.R;

/**
 * View holder for user list item.
 */
public class UserListItemHolder extends BaseViewHolder {

	public final ImageView photoView;
	public final ImageViewContentLoader photoContentLoader;
	public final TextView fullNameView;
	public final TextView actionButton;

	public UserListItemHolder(View itemView) {
		super(itemView);
		photoView = ViewUtils.findView(itemView, R.id.sp_photo);
		photoContentLoader = new UserPhotoLoader(photoView);
		fullNameView = ViewUtils.findView(itemView, R.id.sp_fullName);
		actionButton = ViewUtils.findView(itemView, R.id.sp_actionButton);
	}

}
