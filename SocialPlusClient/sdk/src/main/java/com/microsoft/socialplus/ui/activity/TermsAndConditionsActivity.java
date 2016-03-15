/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.ui.activity;

import com.microsoft.socialplus.sdk.R;
import com.microsoft.socialplus.ui.activity.base.BaseActivity;
import com.microsoft.socialplus.ui.fragment.TextFragment;

/**
 * Shows terms and conditions.
 */
public class TermsAndConditionsActivity extends BaseActivity {

	@Override
	protected void setupFragments() {
		setActivityContent(TextFragment.create(R.string.sp_terms_text));
	}
}
