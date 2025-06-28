package com.example.pruebatecnicaliverpool.data.utilsresponse

import com.example.pruebatecnicafrogtek.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> makeNetWorkCall(
    call: suspend () -> T
): ResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ResponseStatus.Error(R.string.unknow_host_exepcion)
    } catch (e: HttpException) {
        val errorMessage = R.string.unknow_error
        ResponseStatus.Error(errorMessage)
    } catch (e: Exception) {
        val errorMessage = R.string.unknow_error
        ResponseStatus.Error(errorMessage)
    }
}