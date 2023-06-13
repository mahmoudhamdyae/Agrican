package com.example.agrican.data.repository

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
import com.example.agrican.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(

): MainRepository {

    override suspend fun getWeather(): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun getNews(): List<News> {
        TODO("Not yet implemented")
    }

    override suspend fun getDefaultAge() {
        TODO("Not yet implemented")
    }

    override suspend fun orderNewProduct() {
        TODO("Not yet implemented")
    }

    override suspend fun getOrders(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun getTreatments(): List<Treatment> {
        TODO("Not yet implemented")
    }

    override suspend fun joinAsExpert() {
        TODO("Not yet implemented")
    }

    override suspend fun getDiseases(): List<Disease> {
        TODO("Not yet implemented")
    }

    override suspend fun getDisease(): Disease {
        TODO("Not yet implemented")
    }

    override suspend fun getPests(): List<Pest> {
        TODO("Not yet implemented")
    }

    override suspend fun getPest(): Pest {
        TODO("Not yet implemented")
    }

    override suspend fun searchProblem() {
        TODO("Not yet implemented")
    }

    override suspend fun calculateFertilize() {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(): Chat {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage() {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun getFarms(): List<Farm> {
        TODO("Not yet implemented")
    }

    override suspend fun getCrops(): List<Crop> {
        TODO("Not yet implemented")
    }

    override suspend fun getCrop(cropId: String): Crop {
        TODO("Not yet implemented")
    }

    override suspend fun addFarm() {
        TODO("Not yet implemented")
    }

    override suspend fun addCrop() {
        TODO("Not yet implemented")
    }

    override suspend fun observeCrop(): Flow<Crop> {
        TODO("Not yet implemented")
    }

    override suspend fun addTask() {
        TODO("Not yet implemented")
    }
}