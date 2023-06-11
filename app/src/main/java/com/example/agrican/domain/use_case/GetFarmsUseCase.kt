package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Farm

class GetFarmsUseCase {

    suspend operator fun invoke(): List<Farm> {
        return listOf(
            Farm(name = "المزرعة الأولى"),
            Farm(name = "المزرعة الثانية"),
            Farm(name = "المزرعة الثالثة"),
            Farm(name = "المزرعة الرابعة"),
            Farm(name = "المزرعة الخامسة"),
            Farm(name = "المزرعة السادسة"),
            Farm(name = "المزرعة السابعة"),
        )
    }
}