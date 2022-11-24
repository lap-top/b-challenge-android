package com.example.broc.domain.use_case

import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.repository.MailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Use case for post invite with hilt @Inject
class PostInviteUseCase @Inject constructor(private val repository: MailRepository) {
    operator fun invoke(name: String, email: String): Flow<Resource<Boolean>> = flow {
        try { // Response 200
            emit(Resource.Loading())
            repository.postMail(name, email)
            emit(Resource.Success(true))
        } catch (e: HttpException) { // Response 400
            emit(
                // extract error message from body (would be moved to utils)
                Resource.Error(
                    message = JSONObject(
                        e.response()?.errorBody()?.charStream()?.readText()!!
                    ).getString(Constants.ERROR_MESSAGE_KEY)
                )
            )
        } catch (e: IOException) { // Can't connect to API
            emit(Resource.Error(message = "Couldn't reach server."))
        }
    }
}