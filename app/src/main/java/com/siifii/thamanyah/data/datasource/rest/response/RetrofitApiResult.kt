package com.siifii.thamanyah.data.datasource.rest.response

import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T : Any> safeApiResult(
    call: suspend () -> Response<T>,
    place: String
): Result<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body())
        else {
            response.errorBody()?.let {
                val resp = it.string()
                val error = Gson().fromJson(resp, ServerErrorResponse::class.java)
                error?.message?.let { errorMessage ->
                    return Result.Error(Exception(errorMessage))
                }
            }
        }
        return Result.Error(IOException("$place. Error " + response.code() + " " + response.message()))
    } catch (e: IllegalStateException) {
        return Result.Error(IOException("$place. Unexpected response from server"))
    } catch (e: UnknownHostException) {
        return Result.Error(IOException("You don’t seem to be connected to the internet. Please check your connection and try again."))
    } catch (e: Exception) {
        return Result.Error(e)
    }
}