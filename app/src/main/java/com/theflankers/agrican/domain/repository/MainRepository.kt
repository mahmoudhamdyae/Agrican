package com.theflankers.agrican.domain.repository

import com.theflankers.agrican.domain.model.Chat
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.model.Disease
import com.theflankers.agrican.domain.model.Farm
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.News
import com.theflankers.agrican.domain.model.Order
import com.theflankers.agrican.domain.model.Pest
import com.theflankers.agrican.domain.model.Task
import com.theflankers.agrican.domain.model.Treatment
import com.theflankers.agrican.domain.model.User
import com.theflankers.agrican.domain.use_case.GetDefaultAgeResponse
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