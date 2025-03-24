package pe.edu.upc.diligencetech.communications.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.communications.data.remote.MessagesService
import pe.edu.upc.diligencetech.communications.data.remote.dtos.MessageDto
import pe.edu.upc.diligencetech.communications.data.remote.dtos.toMessage
import pe.edu.upc.diligencetech.communications.data.remote.resources.MessageResource
import pe.edu.upc.diligencetech.communications.domain.Messages
import retrofit2.Response
import javax.inject.Inject

class MessagesRepository @Inject constructor(
    private val service: MessagesService,
) {
    suspend fun getMessagesByProjectId(projectId: Long): Resource<List<Messages>> = withContext(Dispatchers.IO) {
        try {
            val response: Response<List<MessageDto>> = service.getMessagesByProjectId(projectId)
            if (response.isSuccessful) {
                val messagesDto = response.body()
                val messages = messagesDto?.map { it.toMessage() }
                messages?.let {
                    return@withContext Resource.Success(it)
                }
            }
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }

    suspend fun getMessageById(messageId: Long): Resource<Messages> {
        return try {
            val response = service.getMessageById(messageId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it.toMessage())
                } ?: Resource.Error("Message not found")
            } else {
                Resource.Error("Error fetching message")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.message}")
        }
    }

    suspend fun getMessagesByUsername(username: String): Resource<List<Messages>> = withContext(Dispatchers.IO) {
        try {
            val response: Response<List<MessageDto>> = service.getMessagesByUserId(username)
            if (response.isSuccessful) {
                val messagesDtos = response.body()
                val messages = messagesDtos?.map { it.toMessage() }
                messages?.let {
                    return@withContext Resource.Success(it)
                }
            }
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }

    suspend fun createMessage(messageDto: MessageResource): Resource<Messages> = withContext(Dispatchers.IO) {
        try {
            val response = service.createMessage(messageDto)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@withContext Resource.Success(it.toMessage())
                }
            }
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }
}