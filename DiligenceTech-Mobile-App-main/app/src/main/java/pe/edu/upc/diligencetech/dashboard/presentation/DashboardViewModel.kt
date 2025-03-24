package pe.edu.upc.diligencetech.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {
    private val _projectCount = MutableStateFlow(0)
    val projectCount: StateFlow<Int> = _projectCount

    private val _totalMembers = MutableStateFlow(0)
    val totalMembers: StateFlow<Int> = _totalMembers

    private val _activeProjectCount = MutableStateFlow(0)
    val activeProjectCount: StateFlow<Int> = _activeProjectCount

    private val _completedProjectCount = MutableStateFlow(0)
    val completedProjectCount: StateFlow<Int> = _completedProjectCount

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val count = repository.getUserProjectCount()
                _projectCount.value = count
                _totalMembers.value = repository.calculateTotalMembers(count)

                val activeCount = repository.getUserActiveProjectCount()
                println("Active Projects Count: $activeCount") // Temporal log
                _activeProjectCount.value = activeCount

                val completedCount = repository.getUserCompletedProjectCount()
                println("Completed Projects Count: $completedCount") // Temporal log
                _completedProjectCount.value = completedCount
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
