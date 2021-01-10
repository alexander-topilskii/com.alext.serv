package com.alext.serv.entities.requests

import kotlinx.serialization.Serializable

@Serializable
class SuccessResponse(val message: String = "success")