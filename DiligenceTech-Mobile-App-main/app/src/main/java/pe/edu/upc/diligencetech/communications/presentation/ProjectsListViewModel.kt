package pe.edu.upc.diligencetech.communications.presentation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.DueDiligenceProjectsRepository
import pe.edu.upc.diligencetech.duediligencemanagement.domain.DueDiligenceProject
import javax.inject.Inject

@HiltViewModel
class ProjectsListViewModel @Inject constructor(
    private val repository: DueDiligenceProjectsRepository
): ViewModel(){
    private var _projects = SnapshotStateList<DueDiligenceProject>()
    val projects: SnapshotStateList<DueDiligenceProject> get() = _projects

    fun getProjects(): Boolean {
        viewModelScope.launch {
            val resource = repository.getDueDiligenceProjectsByUsername()
            if (resource is Resource.Success) {
                _projects.clear()
                resource.data.let {
                    _projects.addAll(it!!.toMutableList())
                }
                return@launch
            }
            else {
                return@launch
            }
        }
        return true
    }
}