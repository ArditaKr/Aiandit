package com.arditakrasniqi.aiandit.data.api


import com.arditakrasniqi.aiandit.data.model.requests.CreateVehicleModel
import com.arditakrasniqi.aiandit.data.model.requests.LoginRequestModel
import com.arditakrasniqi.aiandit.data.model.responses.CreateVehicleResponse
import com.arditakrasniqi.aiandit.data.model.responses.LoginResponseModel
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceAPI {
    @POST(LOGIN)
    suspend fun userLogin(
        @Body loginRequestModel: LoginRequestModel
    ): Response<LoginResponseModel>


    @GET(VEHICLES)
    suspend fun getVehicles(
        @Path("userId") userId: String?
    ): Response<List<Vehicle>>

    @POST(CREATE_VEHICLE)
    suspend fun createVehicle(
        @Path("userId") userId: String?,
        @Body createVehicleModel: CreateVehicleModel
    ): Response<CreateVehicleResponse>


    companion object {
        private const val LOGIN = "login"
        private const val VEHICLES = "users/{userId}/vehicles"
        private const val CREATE_VEHICLE = "users/{userId}/vehicles"
    }
}