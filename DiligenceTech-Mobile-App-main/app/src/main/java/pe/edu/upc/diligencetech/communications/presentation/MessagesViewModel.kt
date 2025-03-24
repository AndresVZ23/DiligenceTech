package pe.edu.upc.diligencetech.communications.presentation

import android.os.Message
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.communications.data.remote.dtos.MessageDto
import pe.edu.upc.diligencetech.communications.data.remote.resources.MessageResource
import pe.edu.upc.diligencetech.communications.data.repositories.MessagesRepository
import pe.edu.upc.diligencetech.communications.domain.Messages
import pe.edu.upc.diligencetech.profiles.data.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val repository: MessagesRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _messages = MutableStateFlow<List<Messages>>(emptyList())
    val messages: StateFlow<List<Messages>> = _messages.asStateFlow()
    private val _userMessages = MutableStateFlow<List<String>>(emptyList())
    val userMessages: StateFlow<List<String>> = _userMessages.asStateFlow()

    fun fetchMessagesForProject(projectId: Long) {
        viewModelScope.launch {
            when (val result = repository.getMessagesByProjectId(projectId)) {
                is Resource.Success -> {
                    _messages.value = result.data ?: emptyList()
                    _messages.value.forEach {
                        val resource = userRepository.getUserById(it.userId)
                        if (resource is Resource.Success) {
                            val user = resource.data?.email ?: ""
                            _userMessages.value = _userMessages.value + user
                        }
                    }
                    Log.d("MessagesViewModel", "Messages: ${_userMessages.value}")
                }
                is Resource.Error -> _messages.value = emptyList()
            }
        }
    }

    fun getMessageById(messageId: Long) = flow {
        val resource = repository.getMessageById(messageId)
        if (resource is Resource.Success) {
            val resource2 = userRepository.getUserById(resource.data?.userId ?: 0)
            if (resource2 is Resource.Success) {
                val user = resource2.data?.email ?: ""
                _userMessages.value = _userMessages.value + user
            }
            emit(resource.data)
        } else {
            emit(null)
        }
    }

    fun createMessage(messageDto: MessageResource) {
        viewModelScope.launch {
            when (val result = repository.createMessage(messageDto)) {
                is Resource.Success -> {
                    result.data?.let { message ->
                        _messages.value = _messages.value + message
                    }
                }
                is Resource.Error -> {
                    fetchMessagesForProject(messageDto.projectId)
                }
            }
        }
    }
}