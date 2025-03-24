package pe.edu.upc.diligencetech.duediligencemanagement.data.remote

import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.DeleteFolderDto
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.DocumentDto
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.DocumentResource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DocumentsService {
    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @PUT("Documents/{DocumentId}")
    suspend fun getDocumentById(@Path("DocumentId") documentId: Long): Response<DocumentDto?>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @DELETE("Documents/{DocumentId}")
    suspend fun deleteDocumentById(@Path("DocumentId") documentId: Long): Call<DeleteFolderDto?>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("Documents")
    suspend fun getAllDocuments(): Response<List<DocumentDto>>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @POST("Documents")
    suspend fun createDocument(@Body documentResource: DocumentResource): Response<DocumentDto?>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("Documents/{folderId}")
    suspend fun getDocumentsByFolderId(@Path("folderId") folderId: Long): Response<List<DocumentDto>>


}