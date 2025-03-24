package pe.edu.upc.diligencetech.profiles.presentation

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.Constants
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.profiles.data.repositories.UserRepository
import pe.edu.upc.diligencetech.profiles.domain.AgentResponse
import pe.edu.upc.diligencetech.profiles.domain.User
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    private val _agentName = MutableStateFlow<AgentResponse?>(null)
    val agentName: StateFlow<AgentResponse?> get() = _agentName

    init {
        fetchUserData()
        fetchAgentName(Constants.id!!)
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            try {
                _user.value = repository.getUserById(Constants.id!!).data
                Log.d("ProfileViewModel", "User data fetched successfully: ${_user.value}")
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error fetching user data", e)
            }
        }
    }

    private fun fetchAgentName(agentId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.getAgentName(agentId)
                if (response is Resource.Success) {
                    _agentName.value = response.data
                } else {
                    Log.e("ProfileViewModel", "Error fetching agent name: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error fetching agent name", e)
            }
        }
    }
}
