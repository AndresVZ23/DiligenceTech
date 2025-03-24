package pe.edu.upc.diligencetech.duediligencemanagement.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.DueDiligenceProjectResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.EditFolderResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.EditProjectResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.FolderResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.DueDiligenceProjectsRepository
import pe.edu.upc.diligencetech.duediligencemanagement.domain.DueDiligenceProject
import pe.edu.upc.diligencetech.profiles.data.repositories.UserRepository
import pe.edu.upc.diligencetech.profiles.domain.User
import javax.inject.Inject

@HiltViewModel
class ProjectsListViewModel @Inject constructor(
    private val repository: DueDiligenceProjectsRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private var _projects = SnapshotStateList<DueDiligenceProject>()
    val projects: SnapshotStateList<DueDiligenceProject> get() = _projects
    private val _newProject = mutableStateOf("")
    val newProject: State<String> get() = _newProject
    private val _rawBuyAgents = mutableStateOf("")
    val rawBuyAgents: State<String> get() = _rawBuyAgents
    private val _rawSellAgents = mutableStateOf("")
    val rawSellAgents: State<String> get() = _rawSellAgents

    suspend fun validateUsersByEmail(emails: List<String>): Resource<List<User>> {
        val validUsers = mutableListOf<User>()
        val errors = mutableListOf<String>()

        for (email in emails) {
            when (val result = userRepository.getUserByEmail(email)) {
                is Resource.Success -> {
                    result.data?.let { validUsers.add(it) }
                }
                is Resource.Error -> {
                    errors.add("El correo $email no corresponde a un usuario existente.")
                }
            }
        }

        return if (errors.isNotEmpty()) {
            Resource.Error(errors.joinToString("\n"))
        } else {
            Resource.Success(validUsers)
        }
    }


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

    fun onNewProjectChange(newProject: String) {
        _newProject.value = newProject
    }

    fun onRawBuyAgentsChange(rawBuyAgents: String) {
        _rawBuyAgents.value = rawBuyAgents
    }

    fun onRawSellAgentsChange(rawSellAgents: String) {
        _rawSellAgents.value = rawSellAgents
    }

    fun addProject(): Boolean {
        // Transformar los correos en listas
        val buyAgents = rawBuyAgents.value.split(",").map { it.trim() }
        val sellAgents = rawSellAgents.value.split(",").map { it.trim() }

        // Estado para rastrear el éxito del proceso
        var isSuccess = true

        viewModelScope.launch {
            try {
                // Validar usuarios por correo electrónico
                val validationResult = validateUsersByEmail(buyAgents + sellAgents)

                if (validationResult is Resource.Success) {
                    val validUsers = validationResult.data ?: emptyList()

                    // Generar roles y agentes con usuarios validados
                    val roles: List<Boolean> = buyAgents.map { true } + sellAgents.map { false }
                    val agents = validUsers.map { it.email }

                    Log.d("Add Project", "Todos los correos son válidos, creando el proyecto")
                    val projectResource = DueDiligenceProjectResource(roles, agents, newProject.value, false)

                    // Crear el proyecto
                    val resource = repository.createDueDiligenceProject(projectResource)
                    if (resource is Resource.Success) {
                        // Actualizar lista de proyectos
                        _projects.clear()
                        resource.data?.let {
                            _projects.addAll(it.toMutableList())
                        }
                        // Limpiar estados
                        _newProject.value = ""
                        _rawBuyAgents.value = ""
                        _rawSellAgents.value = ""
                    } else {
                        isSuccess = false
                        Log.e("Add Project", "Error al crear el proyecto: ${resource.message}")
                    }
                } else if (validationResult is Resource.Error) {
                    isSuccess = false
                    Log.e("Add Project", "Error al validar correos: ${validationResult.message}")
                }
            } catch (e: Exception) {
                isSuccess = false
                Log.e("Add Project", "Excepción: ${e.message}")
            }
        }

        return isSuccess
    }

    fun editProjectActive(projectId: Long, active: Boolean): Boolean {
        viewModelScope.launch {
            val editProjectResource = EditProjectResource(active = active)
            val resource = repository.editProjectActive(projectId, editProjectResource)
            if (resource is Resource.Success) {
                _projects.find { it.id == projectId }?.let { project ->
                    val index = _projects.indexOf(project)
                    _projects[index] = project.copy(active = active)
                }
                return@launch
            } else {
                return@launch
            }
        }
        return true
    }


}