/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.image;

import android.widget.ImageView;

import com.microsoft.socialplus.sdk.R;

/**
 * Implementation of {@link ImageViewContentLoader} for wide image views.
 */
public class CoverLoader extends ImageViewContentLoader {

	public CoverLoader(ImageView imageView) {
		super(imageView);
	}

	@Override
	protected void onBitmapFailed() {
		ImageView imageView = getImageView();
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		imageView.setImageResource(R.drawable.sp_ic_broken_image);
	}

}
