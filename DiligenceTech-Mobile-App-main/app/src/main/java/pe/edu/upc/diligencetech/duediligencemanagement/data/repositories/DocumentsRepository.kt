package pe.edu.upc.diligencetech.duediligencemanagement.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.DocumentsService
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.toDocument
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.DocumentResource
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Document

class DocumentsRepository (
    private val documentsService: DocumentsService
) {
    suspend fun getDocumentsByFolderId(folderId: Long): Resource<List<Document>>
    = withContext(Dispatchers.IO) {
        try {
            val response = documentsService.getDocumentsByFolderId(folderId)
            if (response.isSuccessful) {
                val documentDtos = response.body()
                val documents = documentDtos?.map {
                    it.toDocument()
                }
                if (documents != null) {
                    return@withContext Resource.Success(documents)
                }
            }
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }

    suspend fun createDocument(documentResource: DocumentResource): Resource<List<Document>>
    = withContext(Dispatchers.IO) {
        try {
            val response = documentsService.createDocument(documentResource)
            if (response.isSuccessful) {
                val resource = getDocumentsByFolderId(documentResource.folderId)
                resource.data?.let {
                    return@withContext Resource.Success(it)
                }
            }
            return@withContext Resource.Error("Something went wrong")
        } catch (e: Exception) {
            return@withContext Resource.Error("Something went wrong: ${e.message}")
        }
    }
}