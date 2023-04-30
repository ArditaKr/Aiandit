package com.arditakrasniqi.aiandit.data.repository


import com.arditakrasniqi.aiandit.data.api.ServiceAPI
import com.arditakrasniqi.aiandit.data.model.requests.CreateVehicleModel
import com.arditakrasniqi.aiandit.data.model.requests.LoginRequestModel
import com.arditakrasniqi.aiandit.data.model.responses.CreateVehicleResponse
import com.arditakrasniqi.aiandit.data.model.responses.LoginResponseModel
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val serviceAPI: ServiceAPI) {

    suspend fun userLogin(loginRequestModel: LoginRequestModel): Response<LoginResponseModel> {
        return serviceAPI.userLogin(loginRequestModel)
    }

    suspend fun getVehicles(userId: String): Response<List<Vehicle>> {
        return serviceAPI.getVehicles(userId)
    }

    suspend fun createVehicle(
        userId: String?,
        createVehicleModel: CreateVehicleModel
    ): Response<CreateVehicleResponse> {
        return serviceAPI.createVehicle(userId, createVehicleModel)
    }
}

