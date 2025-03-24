package pe.edu.upc.diligencetech.duediligencemanagement.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.diligencetech.common.Constants
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.DueDiligenceProjectsService
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.toProject
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.DueDiligenceProjectResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.EditFolderResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.EditProjectResource
import pe.edu.upc.diligencetech.duediligencemanagement.domain.DueDiligenceProject

class DueDiligenceProjectsRepository(
    private val service: DueDiligenceProjectsService,
) {
    suspend fun getDueDiligenceProjects(): Resource<List<DueDiligenceProject>>
            = withContext(Dispatchers.IO) {
        try {
            val response = service.getDueDiligenceProjects()
            if (response.isSuccessful) {
                val projectDtos = response.body()
                val projects = projectDtos?.map {
                    it.toProject()
                }
                projects?.let {
                    return@withContext Resource.Success(projects)
                }
            }
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }

    suspend fun createDueDiligenceProject(dueDiligenceProjectResource: DueDiligenceProjectResource): Resource<List<DueDiligenceProject>>
            = withContext(Dispatchers.IO) {
        try {
            Log.d("No falla tan rapido", "${Constants.token}")
            val response = service.createDueDiligenceProject(dueDiligenceProjectResource)
            if (response.isSuccessful) {
                Log.d("Success", "Sorprendentemente")
                val resource = getDueDiligenceProjectsByUsername()
                return@withContext resource
            }
            Log.d("Funciono con Error", "Wow")
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }


    suspend fun getDueDiligenceProjectsByUsername(): Resource<List<DueDiligenceProject>>
            = withContext(Dispatchers.IO) {
        try {
            val response = service.getDueDiligenceProjectsByUsername(Constants.username!!)
            Log.d("No falla tan rapido", "${Constants.token}")
            if (response.isSuccessful) {
                Log.d("Success", "Sorprendentemente")
                val projectDtos = response.body()
                if (projectDtos != null) {
                    Log.d("No es nulo", "$projectDtos")
                }
                val projects = projectDtos?.map {
                    it.toProject()
                }
                Log.d("No es nulo", "$projects")
                projects?.let {
                    return@withContext Resource.Success(projects)
                }
            }
            Log.d("Funciono con Error", "Wow")
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            Log.d("Fallo", "Increiblemente")
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }
    suspend fun getProjectById(projectId: Long): Resource<DueDiligenceProject>
            = withContext(Dispatchers.IO) {
        try {
            val response = service.getDueDiligenceProjectById(projectId)  // Asegúrate de que el servicio tenga esta función
            if (response.isSuccessful) {
                val projectDto = response.body()
                projectDto?.let {
                    val project = it.toProject()
                    return@withContext Resource.Success(project)
                }
            }
            return@withContext Resource.Error("Project not found")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }

    suspend fun editProjectActive(projectId: Long, editProjectResource: EditProjectResource):
            Resource<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = service.editActiveProject(projectId, editProjectResource)
            if (response.isSuccessful) {
                return@withContext Resource.Success(Unit) // Edición exitosa
            }
            return@withContext Resource.Error("Error al editar el estado del Proyecto. Código: ${response.code()}")
        } catch (e: Exception) {
            return@withContext Resource.Error("Error: ${e.message}")
        }
    }
}