/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package com.microsoft.embeddedsocial.pending;

import android.content.Context;

/**
 * An action postponed until the user is signed-in.
 */
public interface PendingAction {
    void execute(Context context);
}
