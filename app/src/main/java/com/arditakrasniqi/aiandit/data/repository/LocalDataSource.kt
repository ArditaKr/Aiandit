package com.arditakrasniqi.aiandit.data.repository


import com.arditakrasniqi.aiandit.data.db.dao.Aianditdao
import com.arditakrasniqi.aiandit.data.model.responses.LoginResponseModel
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val aianditdao: Aianditdao) {
    /**User Data*/
     fun insertUser(loginResponseModel: LoginResponseModel) {
        aianditdao.insertUserData(loginResponseModel)
    }

     fun getUser(token: String): Flow<LoginResponseModel> {
        return aianditdao.getUser(token)
    }

     fun deleteLoginResponse() {
        return aianditdao.deleteLoginResponse()
    }

    /**Vehicle Data*/

     fun insertVehiclesDataToDb(vehicleList: List<Vehicle>) {
        return aianditdao.insertVehiclesDataToDb(vehicleList)
    }
}