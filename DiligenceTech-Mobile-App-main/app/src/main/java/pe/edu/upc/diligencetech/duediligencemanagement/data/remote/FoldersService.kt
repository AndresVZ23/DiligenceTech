package pe.edu.upc.diligencetech.duediligencemanagement.data.remote

import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos.FolderDto
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.EditFolderResource
import pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources.FolderResource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FoldersService {
    @Headers(
        value = [
            "accept: application/json",
        ]
    )
    @PUT("folders/{folderId}")
    suspend fun editFolder(
        @Path("folderId") folderId: Long,
        @Body editFolderResource: EditFolderResource
    ): Response<FolderDto?>

    @Headers(
        value = [
            "accept: application/json",
        ]
    )
    @GET("folders")
    suspend fun getAllFolders(): Response<List<FolderDto>>

    @Headers(
        value = [
            "accept: application/json",
        ]
    )
    @POST("folders")
    suspend fun createFolder(@Body folderResource: FolderResource): Response<FolderDto?>

    @Headers(
        value = [
            "accept: application/json",
        ]
    )
    @GET("folders/{areaId}")
    suspend fun getFoldersByAreaId(@Path("areaId") areaId: Long): Response<List<FolderDto>>

    @Headers(
        value = [
            "accept: application/json",
        ]
    )
    @GET("folders/get-id/{folderId}")
    suspend fun getFolderById(@Path("folderId") folderId: Long): Response<FolderDto>
}