/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.microsoft.autorest.models.IdentityProvider;
import com.microsoft.socialplus.base.GlobalObjectRegistry;
import com.microsoft.socialplus.base.utils.debug.DebugLog;
import com.microsoft.socialplus.sdk.R;
import com.microsoft.socialplus.server.IAuthenticationService;
import com.microsoft.socialplus.server.SocialPlusServiceProvider;
import com.microsoft.socialplus.server.exception.NetworkRequestException;
import com.microsoft.socialplus.server.model.auth.GetThirdPartyTokenRequest;
import com.microsoft.socialplus.server.model.auth.ThirdPartyTokenResponse;
import com.microsoft.socialplus.ui.activity.WebAuthenticationActivity;
import com.microsoft.socialplus.ui.util.SocialNetworkAccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implements Twitter authentication process using web views.
 */
public class TwitterWebAuthenticator extends AbstractAuthenticator {

	private static final int RC_WEB_AUTH_REQUEST = 1000;

	private static final Uri BASE_TWITTER_AUTH_URI = Uri.parse("https://api.twitter.com/oauth/authorize");
	private static final String PARAM_OAUTH_TOKEN = "oauth_token";
	private static final String PARAM_OAUTH_VERIFIER = "oauth_verifier";
	private static final String PARAM_FORCE_LOGIN = "force_login";
	private static final String FORCE_LOGIN = "true";
	private static final String TWITTER_AUTH_URL_ERROR_MARKER = "error";

	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private final IAuthenticationService authService = GlobalObjectRegistry
		.getObject(SocialPlusServiceProvider.class)
		.getAuthenticationService();

	private String requestToken;

	public TwitterWebAuthenticator(Fragment fragment, IAuthenticationCallback authCallback) {
		super(IdentityProvider.TWITTER, fragment, authCallback);
	}

	@Override
	public void dispose() {
		executor.shutdown();
		super.dispose();
	}

	@Override
	protected void onAuthenticationStarted() throws AuthenticationException {
		executor.submit(() -> {
			try {
				GetThirdPartyTokenRequest request = new GetThirdPartyTokenRequest(IdentityProvider.TWITTER);
				ThirdPartyTokenResponse response = authService.getThirdPartyRequestToken(request);
				requestToken = response.getRequestToken();
			} catch (NetworkRequestException e) {
				DebugLog.logException(e);
				onGeneralAuthenticationError();
				return;
			}
			String url = buildTwitterAuthUrl(requestToken);
			Intent intent = new Intent(getFragment().getActivity(), WebAuthenticationActivity.class)
				.putExtra(WebAuthenticationActivity.EXTRA_AUTH_URL, url)
				.putExtra(WebAuthenticationActivity.EXTRA_AUTH_MODE,
					WebAuthenticationActivity.AuthMode.STOP_ON_FIRST_REDIRECT.ordinal());
			getFragment().startActivityForResult(intent, RC_WEB_AUTH_REQUEST);
		});
	}

	private String buildTwitterAuthUrl(String oauthToken) {
		return BASE_TWITTER_AUTH_URI.buildUpon()
			.appendQueryParameter(PARAM_OAUTH_TOKEN, oauthToken)
			.appendQueryParameter(PARAM_FORCE_LOGIN, FORCE_LOGIN)
			.toString();
	}

	@Override
	public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RC_WEB_AUTH_REQUEST) {
			if (resultCode == Activity.RESULT_OK) {
				onAuthenticationResultObtained(data.getStringExtra(WebAuthenticationActivity.EXTRA_RESULT_URL));
			} else {
				onAuthenticationError(getFragment().getString(R.string.sp_msg_general_signin_cancelled));
			}
			return true;
		} else {
			return false;
		}
	}

	private void onAuthenticationResultObtained(String resultUrl) {
		if (resultUrl.contains(TWITTER_AUTH_URL_ERROR_MARKER)) {
			onGeneralAuthenticationError();
			return;
		}
		Uri uri = Uri.parse(resultUrl);
		String oauthToken = uri.getQueryParameter(PARAM_OAUTH_TOKEN);
		String oauthVerifier = uri.getQueryParameter(PARAM_OAUTH_VERIFIER);
		if (requestToken.equals(oauthToken) && !TextUtils.isEmpty(oauthVerifier)) {
			SocialNetworkAccount account = SocialNetworkAccount.fromOauthTokenAndVerifier(
				IdentityProvider.TWITTER,
				requestToken,
				oauthVerifier
			);
			onAuthenticationSuccess(account);
		} else {
			onGeneralAuthenticationError();
		}
	}
}
