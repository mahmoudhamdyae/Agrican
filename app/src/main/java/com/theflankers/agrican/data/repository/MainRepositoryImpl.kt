package com.theflankers.agrican.data.repository

import com.theflankers.agrican.data.remote.ApiService
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.model.Disease
import com.theflankers.agrican.domain.model.Farm
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.MessageType
import com.theflankers.agrican.domain.model.News
import com.theflankers.agrican.domain.model.Order
import com.theflankers.agrican.domain.model.Pest
import com.theflankers.agrican.domain.model.Task
import com.theflankers.agrican.domain.model.Treatment
import com.theflankers.agrican.domain.model.User
import com.theflankers.agrican.domain.repository.MainRepository
import com.theflankers.agrican.domain.use_case.GetDefaultAgeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): MainRepository {

    override suspend fun getCurrentUser(userId: String): User {
        return apiService.getCurrentUser(userId)
    }

    override suspend fun createUser(user: User) {
        apiService.createUser(user)
    }

    override suspend fun getNews(): List<News> {
        return apiService.getNews()
    }

    override suspend fun getDefaultAge(): GetDefaultAgeResponse {
        return apiService.getDefaultAge()
    }

    override suspend fun orderNewProduct() {
        apiService.orderNewProduct()
    }

    override suspend fun getOrders(): List<Order> {
        return apiService.getOrders()
    }

    override suspend fun getOrder(orderId: String): Order {
        return apiService.getOrder(orderId)
    }

    override suspend fun getTreatments(): List<Treatment> {
        return apiService.getTreatments()
    }

    override suspend fun joinAsExpert(
        fullName: String,
        email: String,
        phoneNumber: String,
        image: String?
    ) {
        apiService.joinAsExpert(
            fullName,
            email,
            phoneNumber,
            image
        )
    }

    override suspend fun getDiseases(): List<Disease> {
        return apiService.getDiseases()
    }

    override suspend fun getDisease(diseaseId: String): Disease {
        return apiService.getDisease(diseaseId)
    }

    override suspend fun getPests(): List<Pest> {
        return apiService.getPests()
    }

    override suspend fun getPest(pestId: String): Pest {
        return apiService.getPest(pestId)
    }

    override suspend fun searchProblem(
        crop: Crop,
        image1: String?,
        image2: String?,
        image3: String?,
    ) {
        apiService.searchProblem(image1, image2, image3)
    }

    override suspend fun calculateFertilize() {
        apiService.calculateFertilize()
    }

    override suspend fun getChat(): Flow<List<Message>> {
//        return apiService.getChat()
        return flow { emit(messages) }
    }

    private val messages = mutableListOf(Message(
                    body = "أهلا بيك معاك .. من أجريكان أقدر أساعد حضرتك إزاى؟",
                    userId = "1",
                    type = MessageType.TEXT
                ),)
    override suspend fun sendMessage(message: Message) {
//        apiService.sendMessage(message)
        messages.add(message)
    }

    override suspend fun getFarms(): List<Farm> {
        return apiService.getFarms()
    }

    override suspend fun getCrops(): List<Crop> {
        return apiService.getCrops()
    }

    override suspend fun getCrop(cropId: String): Crop {
        return apiService.getCrop(cropId)
    }

    override suspend fun addFarm(farm: Farm) {
        apiService.addFarm(farm)
    }

    override suspend fun addCrop(crop: Crop) {
        return apiService.addCrop(crop)
    }

    override suspend fun observeCrop(): Flow<Crop> {
        return apiService.observeCrop()
    }

    override suspend fun addTask(task: Task) {
        apiService.addTask(task)
    }
}