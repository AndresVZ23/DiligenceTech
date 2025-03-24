package pe.edu.upc.diligencetech.duediligencemanagement.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.AreaResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.EditAreaResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.AreasRepository
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.DueDiligenceProjectsRepository
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Area
import javax.inject.Inject

@HiltViewModel
class AreasListViewModel @Inject constructor(
    private val repository: AreasRepository,
    private val projectsRepository: DueDiligenceProjectsRepository // Agregamos el repositorio de proyectos
): ViewModel() {

    private var _areas = SnapshotStateList<Area>()
    val areas: SnapshotStateList<Area> get() = _areas

    private val _newArea = mutableStateOf("")
    val newArea: State<String> get() = _newArea

    private val _selectedProjectName = mutableStateOf("")
    val selectedProjectName: State<String> get() = _selectedProjectName

    private val _selectedProjectId = mutableStateOf<Long?>(null)
    val selectedProjectId: State<Long?> get() = _selectedProjectId

    fun getAreas(projectId: Long): Boolean {
        viewModelScope.launch {
            _selectedProjectId.value = projectId
            val projectResource = projectsRepository.getProjectById(projectId)
            if (projectResource is Resource.Success) {
                _selectedProjectName.value = projectResource.data?.projectName ?: "Nombre desconocido"
            }

            val resource = repository.getAreasByProjectId(projectId)
            if (resource is Resource.Success) {
                _areas.clear()
                resource.data.let {
                    _areas.addAll(it!!.toMutableList())
                }
                return@launch
            } else {
                return@launch
            }
        }
        return true
    }

    fun onNewAreaChange(newArea: String) {
        _newArea.value = newArea
    }

    fun addArea(projectId: Long): Boolean {
        viewModelScope.launch {
            val areaResource = AreaResource(projectId, newArea.value)
            val resource = repository.createArea(areaResource)
            if (resource is Resource.Success) {
                _areas.clear()
                resource.data.let {
                    _areas.addAll(it!!.toMutableList())
                }
                _newArea.value = ""
                return@launch
            } else {
                return@launch
            }
        }
        return true
    }

    fun editArea(areaId: Long, name: String): Boolean {
        viewModelScope.launch {
            val editAreaResource = EditAreaResource(name = name) // Crear el recurso para la actualización
            val resource = repository.editArea(areaId, editAreaResource) // Llamar al repositorio para actualizar el área
            if (resource is Resource.Success) {
                // Actualizar la lista de áreas si la edición es exitosa
                _areas.find { it.id == areaId }?.let { area ->
                    val index = _areas.indexOf(area)
                    _areas[index] = area.copy(name = name)
                }
                return@launch
            } else {
                // Manejar el error según sea necesario
                return@launch
            }
        }
        return true
    }

}
