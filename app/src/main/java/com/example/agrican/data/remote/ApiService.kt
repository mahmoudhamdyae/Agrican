package com.example.agrican.data.remote

import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Disease
import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.Order
import com.example.agrican.domain.model.Pest
import com.example.agrican.domain.model.Task
import com.example.agrican.domain.model.Treatment
import com.example.agrican.domain.model.User
import com.example.agrican.domain.use_case.GetDefaultAgeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("")
    suspend fun getCurrentUser(userId: String): User

    @GET("")
    suspend fun createUser(user: User)

    @GET("")
    suspend fun getNews(): List<News>

    @GET("")
    suspend fun getDefaultAge(): GetDefaultAgeResponse

    @POST("")
    suspend fun orderNewProduct()

    @GET("")
    suspend fun getOrders(): List<Order>

    @GET("")
    suspend fun getOrder(orderId: String): Order

    @GET("")
    suspend fun getTreatments(): List<Treatment>

    @POST("")
    suspend fun joinAsExpert(
        fullName: String,
        email: String,
        phoneNumber: String,
        image: String?
    )

    @GET("")
    suspend fun getDiseases(): List<Disease>

    @GET("")
    suspend fun getDisease(diseaseId: String): Disease

    @GET("")
    suspend fun getPests(): List<Pest>

    @GET("")
    suspend fun getPest(pestId: String): Pest

    @POST("")
    suspend fun searchProblem(
        image1: String?,
        image2: String?,
        image3: String?,
    )

    @GET("")
    suspend fun calculateFertilize()

    @GET("")
    suspend fun getChat(): Flow<Chat>

    @POST("")
    suspend fun sendMessage(message: Message)

    @POST("")
    suspend fun getFarms(): List<Farm>

    @POST("")
    suspend fun getCrops(): List<Crop>

    @POST("")
    suspend fun getCrop(cropId: String): Crop

    @POST("")
    suspend fun addFarm(farm: Farm)

    @POST("")
    suspend fun addCrop(crop: Crop)

    @POST("")
    suspend fun observeCrop(): Flow<Crop>

    @POST("")
    suspend fun addTask(task: Task)
}