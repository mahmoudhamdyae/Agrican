package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Task
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(taskName: String) {
        val task = Task(taskName = taskName)
        mainRepository.addTask(task)
    }
}