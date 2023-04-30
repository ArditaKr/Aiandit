package com.arditakrasniqi.aiandit.presentation.ui.login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.arditakrasniqi.aiandit.data.model.requests.LoginRequestModel
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
class LoginViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
    private val sharedPreferences: AppSharedPreferences
) : AndroidViewModel(application) {

    /** Room Database */
    //val userDataFromDb: LiveData<LoginResponseModel> = repository.local.getUser(sharedPreferences.token!!).asLiveData()

    val userLogin: MutableLiveData<DataState<LoginResponseModel>> = MutableLiveData()

    fun login(loginRequestModel: LoginRequestModel) = viewModelScope.launch {
        userLogin.value = DataState.Loading()
        try {
            val response = repository.remote.userLogin(loginRequestModel)
            if(response.isSuccessful){
                if(response.body() != null){
                    insertUserDataToDb(response.body()!!)
                    userLogin.value = DataState.Success(response.body()!!)
                }
            }else{
                userLogin.value = DataState.Error(Errors.UNKNOWN, response.message().toString())
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> userLogin.value =
                    DataState.Error(Errors.SERVER, "Server Problem")
                is ConnectException, is UnknownHostException -> userLogin.value =
                    DataState.Error(Errors.NETWORK, "No internet connection")
                is SocketTimeoutException -> userLogin.value =
                    DataState.Error(Errors.TIMEOUT, "Timeout")
                is SocketException -> userLogin.value =
                    DataState.Error(Errors.TIMEOUT, "Internet connection lost!")
                else -> {
                    userLogin.value = DataState.Error(Errors.UNKNOWN, e.message.toString())
                }
            }
        }
    }

    private fun insertUserDataToDb(loginResponseModel: LoginResponseModel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertUser(loginResponseModel)
        }

    fun saveTokenAndId(
        token: String?,
        userId: String?
    ) {
        sharedPreferences.token = token
        sharedPreferences.id = userId
    }
}