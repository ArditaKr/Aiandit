package com.arditakrasniqi.aiandit.presentation.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arditakrasniqi.aiandit.data.db.app.AppDatabase
import com.arditakrasniqi.aiandit.data.model.requests.CreateVehicleModel
import com.arditakrasniqi.aiandit.data.model.responses.CreateVehicleResponse
import com.arditakrasniqi.aiandit.data.model.responses.LoginResponseModel
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import com.arditakrasniqi.aiandit.data.repository.Repository
import com.arditakrasniqi.aiandit.presentation.utils.AppSharedPreferences
import com.arditakrasniqi.aiandit.presentation.utils.DataState
import com.arditakrasniqi.aiandit.presentation.utils.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
    private val sharedPreferences: AppSharedPreferences
) : AndroidViewModel(application) {
    val vehicles: MutableLiveData<DataState<Response<List<Vehicle>>>> = MutableLiveData()
    val createVehicles: MutableLiveData<DataState<Response<CreateVehicleResponse>>> = MutableLiveData()

    fun getVehiclesFromAPI(userId: String) = viewModelScope.launch {
        vehicles.value = DataState.Loading()
        try {
            val response = repository.remote.getVehicles(userId)
            if (response.isSuccessful) {
                    insertVehiclesDataToDb(response.body()!!)
                    vehicles.value = DataState.Success(response)

            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> vehicles.value =
                    DataState.Error(Errors.SERVER, "Problem i serverit ")
                is ConnectException, is UnknownHostException -> vehicles.value =
                    DataState.Error(Errors.NETWORK, "Nuk keni internet")
                is SocketTimeoutException -> vehicles.value =
                    DataState.Error(Errors.TIMEOUT, "Timeout")
                is SocketException -> vehicles.value =
                    DataState.Error(Errors.TIMEOUT, "Lidhja me internet u ndërpre!")
                else -> {
                    vehicles.value = DataState.Error(Errors.UNKNOWN, "Problem i panjohur!")
                }
            }
        }
    }

    fun createVehicle(userId: String, createVehicleModel: CreateVehicleModel) =
        viewModelScope.launch {
            createVehicles.value = DataState.Loading()
            try {
                val response = repository.remote.createVehicle(userId, createVehicleModel)
                if (response.isSuccessful) {
                    createVehicles.value = DataState.Success(response)
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> createVehicles.value =
                        DataState.Error(Errors.SERVER, "Server Problem")
                    is ConnectException, is UnknownHostException -> createVehicles.value =
                        DataState.Error(Errors.NETWORK, "No internet connection")
                    is SocketTimeoutException -> createVehicles.value =
                        DataState.Error(Errors.TIMEOUT, "Timeout")
                    is SocketException -> createVehicles.value =
                        DataState.Error(Errors.TIMEOUT, "Internet connection lost!")
                    else -> {
                        createVehicles.value = DataState.Error(Errors.UNKNOWN, "Unknown Problem")
                    }
                }
            }
        }

    private fun insertVehiclesDataToDb(vehicle: List<Vehicle>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertVehiclesDataToDb(vehicle)
        }

     fun deleteLoggedInUserDataFromDb() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteLoginResponse()
        }

    fun getUserId(): String? {
        return sharedPreferences.id
    }
}