package com.example.agrican.domain.repository

import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Disease
import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.Order
import com.example.agrican.domain.model.Pest
import com.example.agrican.domain.model.Treatment
import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getWeather(): Weather
    suspend fun getNews(): List<News>

    suspend fun getDefaultAge()
    suspend fun orderNewProduct()
    suspend fun getOrders(): List<Order>
    suspend fun getTreatments(): List<Treatment>
    suspend fun joinAsExpert()
    suspend fun getDiseases(): List<Disease>
    suspend fun getDisease(): Disease
    suspend fun getPests(): List<Pest>
    suspend fun getPest(): Pest

    suspend fun searchProblem()
    suspend fun calculateFertilize()
    suspend fun getChat(): Chat
    suspend fun sendMessage()

    suspend fun getCurrentUser(): User
    suspend fun getFarms(): List<Farm>
    suspend fun getCrops(): List<Crop>
    suspend fun getCrop(cropId: String): Crop
    suspend fun addFarm()
    suspend fun addCrop()
    suspend fun observeCrop(): Flow<Crop>
    suspend fun addTask()
}