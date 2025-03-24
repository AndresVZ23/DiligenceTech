package pe.edu.upc.diligencetech.duediligencemanagement.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.AreaResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.DocumentResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.FolderResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.AreasRepository
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.DocumentsRepository
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.DueDiligenceProjectsRepository
import pe.edu.upc.diligencetech.duediligencemanagement.data.repositories.FoldersRepository
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Area
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Document
import javax.inject.Inject

@HiltViewModel
class FilesListViewModel @Inject constructor(
    private val repository: DocumentsRepository,
    private val foldersRepository: FoldersRepository
): ViewModel() {

    val storage = Firebase.storage
    val storageRef = storage.reference

    private var _fileUris = SnapshotStateList<Uri>()
    val fileUris: SnapshotStateList<Uri> get() = _fileUris

    private var _files = SnapshotStateList<Document>()
    val files: SnapshotStateList<Document> get() = _files

    private val _newFile = mutableStateOf("")
    val newFile: State<String> get() = _newFile

    private val _selectedProjectName = mutableStateOf("")
    val selectedProjectName: State<String> get() = _selectedProjectName

    private val _selectedFolderName = mutableStateOf("")
    val selectedFolderName: State<String> get() = _selectedFolderName

    fun onInit(folderId: Long, projectName: String): Boolean {
        onFolderSelected(folderId)
        getFiles(folderId, projectName)
        return true
    }

    fun onFolderSelected(folderId: Long): Boolean {
        viewModelScope.launch {
            val resource = foldersRepository.getFolderById(folderId)
            if (resource is Resource.Success) {
                _selectedFolderName.value = resource.data?.name ?: ""
            }
        }
        return true
    }

    fun getFiles(folderId: Long, projectName: String): Boolean {
        viewModelScope.launch {
            // Usamos el projectName directamente
            _selectedProjectName.value = projectName

            val resource = repository.getDocumentsByFolderId(folderId)
            if (resource is Resource.Success) {
                _files.clear()
                resource.data?.let {
                    _files.addAll(it.toMutableList())
                }
                return@launch
            } else {
                return@launch
            }
        }
        return true
    }

    fun onNewFileChange(newFolder: String) {
        _newFile.value = newFolder
    }

    private suspend fun uploadFilesToFirebase(folderId: Long) {
        val downloadUrls = mutableListOf<Uri>()
        fileUris.forEach { uri ->
            try {
                // Firebase
                val storageRefChild = storageRef.child("${folderId}/${uri.lastPathSegment}")
                storageRefChild.putFile(uri).await()
                val downloadUrl = storageRefChild.downloadUrl.await()
                Log.d("FilesListViewModel", "Download URL: $downloadUrl")
                // Backend
                try {
                    val documentResource = DocumentResource(
                        folderId = folderId,
                        filename = downloadUrl.lastPathSegment ?: "unknown",
                        fileUrl = downloadUrl.toString()
                    )
                    Log.d("FilesListViewModel", "Resource: $documentResource")
                    val resource = repository.createDocument(documentResource)
                    if (resource is Resource.Success) {
                        Log.d("FilesListViewModel", "Document added successfully")
                    } else {
                        Log.d("FilesListViewModel", "Error adding document")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        getFiles(folderId, selectedProjectName.value)
    }

    fun addFile(folderId: Long): Boolean {
        viewModelScope.launch {
            // Firebase Firestore
            uploadFilesToFirebase(folderId)
            return@launch
        }
        return true
    }
}