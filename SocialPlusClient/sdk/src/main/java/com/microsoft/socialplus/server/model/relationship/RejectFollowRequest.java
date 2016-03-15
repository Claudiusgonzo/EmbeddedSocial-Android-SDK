/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.server.model.relationship;

import com.microsoft.rest.ServiceException;
import com.microsoft.rest.ServiceResponse;

import java.io.IOException;

import retrofit2.Response;

public class RejectFollowRequest extends UserRelationshipRequest {

    public RejectFollowRequest(String relationshipUserHandle) {
        super(relationshipUserHandle);
    }

    @Override
    public Response send() throws ServiceException, IOException {
        ServiceResponse<Object> serviceResponse =
                PENDING.deletePendingUser(relationshipUserHandle, bearerToken);
        return serviceResponse.getResponse();
    }
}
