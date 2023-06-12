package com.example.agrican.domain.repository

interface MainRepository {

    suspend fun getWeather()
    suspend fun getNews()

    suspend fun getDefaultAge()
    suspend fun orderNew()
    suspend fun getOrders()
    suspend fun getTreatments()
    suspend fun joinAsExpert()
    suspend fun getDiseases()
    suspend fun getDisease()
    suspend fun getPests()
    suspend fun getPest()

    suspend fun searchProblem()
    suspend fun calculateFertilize()
    suspend fun getChat()
    suspend fun sendMessage()

    suspend fun getCurrentUser()
    suspend fun getFarms()
    suspend fun getCrops()
    suspend fun addFarm()
    suspend fun addCrop()
    suspend fun observeCrop()
    suspend fun addTask()
}