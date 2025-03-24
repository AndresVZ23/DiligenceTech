package pe.edu.upc.diligencetech.profiles.data.remote

import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.UserDto
import pe.edu.upc.diligencetech.profiles.domain.AgentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersService {

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId")userId: Long): Response<UserDto>

    @GET("users/email/{email}")
    suspend fun getUserByEmail(@Path("email")email: String): Response<UserDto>

    @GET("profiles/get/{agentId}")
    suspend fun getAgentName(@Path("agentId")agentId: Long): Response<AgentResponse>
}

