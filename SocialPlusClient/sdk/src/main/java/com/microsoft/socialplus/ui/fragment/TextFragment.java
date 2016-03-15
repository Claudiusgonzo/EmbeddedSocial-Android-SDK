/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.microsoft.socialplus.sdk.R;
import com.microsoft.socialplus.service.IntentExtras;
import com.microsoft.socialplus.ui.fragment.base.BaseFragment;

/**
 * Shows a text.
 */
public class TextFragment extends BaseFragment {
	@Override
	protected int getLayoutId() {
		return R.layout.sp_fragment_text;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null && arguments.containsKey(IntentExtras.TEXT_RES_ID)) {
			findView(view, R.id.sp_text, TextView.class).setText(arguments.getInt(IntentExtras.TEXT_RES_ID));
		}
	}

	public static TextFragment create(@StringRes int textId) {
		TextFragment fragment = new TextFragment();
		Bundle arguments = new Bundle();
		arguments.putInt(IntentExtras.TEXT_RES_ID, textId);
		fragment.setArguments(arguments);
		return fragment;
	}
}
