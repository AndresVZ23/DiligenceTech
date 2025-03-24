package pe.edu.upc.diligencetech.profiles.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.toUser
import pe.edu.upc.diligencetech.profiles.data.remote.UsersService
import pe.edu.upc.diligencetech.profiles.domain.AgentResponse
import pe.edu.upc.diligencetech.profiles.domain.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val usersService: UsersService
) {
    suspend fun getUserById(id: Long): Resource<User> = withContext(Dispatchers.IO) {
        try{
            val response = usersService.getUserById(id)
            if(response.isSuccessful){
                response.body()?.let { userDto ->
                    return@withContext Resource.Success(data = userDto.toUser())
                }
                return@withContext Resource.Error("No se encontró el usuario")
            }
            return@withContext Resource.Error(response.message())
        }catch (e: Exception){
            return@withContext Resource.Error(e.message ?: "Ocurrió un error")
        }
    }

    suspend fun getUserByEmail(email: String): Resource<User> = withContext(Dispatchers.IO) {
        try{
            val response = usersService.getUserByEmail(email)
            if(response.isSuccessful){
                response.body()?.let { userDto ->
                    return@withContext Resource.Success(data = userDto.toUser())
                }
                return@withContext Resource.Error("No se encontró el usuario")
            }
            return@withContext Resource.Error(response.message())
        }catch (e: Exception){
            return@withContext Resource.Error(e.message ?: "Ocurrió un error")
        }
    }

    suspend fun getAgentName(agentId: Long): Resource<AgentResponse> = withContext(Dispatchers.IO) {
        try {
            val response = usersService.getAgentName(agentId)
            if (response.isSuccessful) {
                response.body()?.let { agentResponse ->
                    return@withContext Resource.Success(data = agentResponse)
                }
                return@withContext Resource.Error("No se encontró el agente")
            }
            return@withContext Resource.Error(response.message())
        } catch (e: Exception) {
            return@withContext Resource.Error(e.message ?: "Ocurrió un error")
        }
    }
}
