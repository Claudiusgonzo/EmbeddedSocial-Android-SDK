/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package com.microsoft.embeddedsocial.server.model.relationship;

import com.microsoft.embeddedsocial.server.exception.NetworkRequestException;
import es_private.com.microsoft.rest.ServiceException;
import es_private.com.microsoft.rest.ServiceResponse;

import java.io.IOException;

import retrofit2.Response;

public class RejectFollowRequest extends UserRelationshipRequest {

    public RejectFollowRequest(String relationshipUserHandle) {
        super(relationshipUserHandle);
    }

    @Override
    public Response send() throws NetworkRequestException {
        ServiceResponse<Object> serviceResponse;
        try {
            serviceResponse = PENDING.deletePendingUser(relationshipUserHandle, authorization);
        } catch (ServiceException|IOException e) {
            throw new NetworkRequestException(e.getMessage());
        }
        checkResponseCode(serviceResponse);

        return serviceResponse.getResponse();
    }
}
