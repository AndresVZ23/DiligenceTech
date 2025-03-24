package pe.edu.upc.diligencetech.communications.data.remote

import pe.edu.upc.diligencetech.communications.data.remote.dtos.MessageDto
import pe.edu.upc.diligencetech.communications.data.remote.resources.MessageResource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface MessagesService {
    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("messages")
    suspend fun getAllMessages(): Call<List<MessageDto>>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @POST("messages")
    suspend fun createMessage(@Body messageDto: MessageResource): Response<MessageDto?>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("messages/{id}")
    suspend fun getMessageById(@Path("id") messageId: Long): Response<MessageDto?>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("messages/project/{projectId}")
    suspend fun getMessagesByProjectId(@Path("projectId") projectId: Long): Response<List<MessageDto>>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("messages/{emailId}")
    suspend fun getMessagesByUserId(@Path("emailId") username: String): Response<List<MessageDto>>
}