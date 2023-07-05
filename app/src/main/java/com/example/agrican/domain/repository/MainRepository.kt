package com.example.agrican.domain.repository

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

interface MainRepository {

    suspend fun getCurrentUser(userId: String): User
    suspend fun createUser(user: User)

    suspend fun getNews(): List<News>

    suspend fun getDefaultAge(): GetDefaultAgeResponse
    suspend fun orderNewProduct()
    suspend fun getOrders(): List<Order>
    suspend fun getOrder(orderId: String): Order
    suspend fun getTreatments(): List<Treatment>
    suspend fun joinAsExpert(fullName: String, email: String, phoneNumber: String, image: String?)
    suspend fun getDiseases(): List<Disease>
    suspend fun getDisease(diseaseId: String): Disease
    suspend fun getPests(): List<Pest>
    suspend fun getPest(pestId: String): Pest

    suspend fun searchProblem(crop: Crop, image1: String?, image2: String?, image3: String?)
    suspend fun calculateFertilize()
    suspend fun getChat(): Flow<Chat>
    suspend fun sendMessage(message: Message)

    suspend fun getFarms(): List<Farm>
    suspend fun getCrops(): List<Crop>
    suspend fun getCrop(cropId: String): Crop
    suspend fun addFarm(farm: Farm)
    suspend fun addCrop(crop: Crop)
    suspend fun observeCrop(): Flow<Crop>
    suspend fun addTask(task: Task)
}