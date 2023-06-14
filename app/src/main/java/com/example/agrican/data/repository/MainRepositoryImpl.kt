package com.example.agrican.data.repository

import com.example.agrican.data.remote.ApiService
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
import com.example.agrican.domain.model.Weather
import com.example.agrican.domain.repository.MainRepository
import com.example.agrican.domain.use_case.GetDefaultAgeResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
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

    override suspend fun getWeather(): Weather {
        return apiService.getWeather()
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
        image: File
    ) {
        apiService.joinAsExpert(
            fullName,
            email,
            phoneNumber,
            MultipartBody.Part.createFormData("image", image.name, image.asRequestBody())
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
        image1: File,
        image2: File,
        image3: File,
    ) {
        apiService.searchProblem(
            MultipartBody.Part.createFormData("image", image1.name, image1.asRequestBody()),
            MultipartBody.Part.createFormData("image", image2.name, image2.asRequestBody()),
            MultipartBody.Part.createFormData("image", image3.name, image3.asRequestBody())
        )
    }

    override suspend fun calculateFertilize() {
        apiService.calculateFertilize()
    }

    override suspend fun getChat(): Flow<Chat> {
        return apiService.getChat()
    }

    override suspend fun sendMessage(message: Message) {
        apiService.sendMessage(message)
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