package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Task
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(taskName: String) {
        val task = Task(taskName = taskName)
        mainRepository.addTask(task)
    }
}