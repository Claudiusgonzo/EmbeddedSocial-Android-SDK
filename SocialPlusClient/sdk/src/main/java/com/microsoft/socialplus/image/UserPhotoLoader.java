/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.image;

import android.widget.ImageView;

import com.microsoft.socialplus.sdk.R;
import com.microsoft.socialplus.ui.theme.ThemeAttributes;


/**
 * Implementation of {@link ImageViewContentLoader} for user's photo views.
 */
public class UserPhotoLoader extends ImageViewContentLoader {

	private final int errorDrawableId;

	public UserPhotoLoader(ImageView imageView) {
		this(imageView, ThemeAttributes.getResourceId(imageView.getContext(), R.styleable.sp_AppTheme_sp_userNoPhotoIcon));
	}

	public UserPhotoLoader(ImageView imageView, int errorDrawableId) {
		super(imageView);
		this.errorDrawableId = errorDrawableId;
	}

	@Override
	protected void onBitmapFailed() {
		getImageView().setImageResource(errorDrawableId);
	}
}
