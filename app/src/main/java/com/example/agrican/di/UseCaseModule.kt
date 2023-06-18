package com.example.agrican.di

import com.example.agrican.domain.repository.AccountService
import com.example.agrican.domain.repository.MainRepository
import com.example.agrican.domain.use_case.AddCropUseCase
import com.example.agrican.domain.use_case.AddFarmUseCase
import com.example.agrican.domain.use_case.AddTaskUseCase
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.domain.use_case.CalculateFertilizersUseCase
import com.example.agrican.domain.use_case.GetChatUseCase
import com.example.agrican.domain.use_case.GetCropUseCase
import com.example.agrican.domain.use_case.GetCropsUseCase
import com.example.agrican.domain.use_case.GetCurrentUserUseCase
import com.example.agrican.domain.use_case.GetDefaultAgeUseCase
import com.example.agrican.domain.use_case.GetDisease
import com.example.agrican.domain.use_case.GetDiseases
import com.example.agrican.domain.use_case.GetFarmsUseCase
import com.example.agrican.domain.use_case.GetNewsUseCase
import com.example.agrican.domain.use_case.GetOrderUseCase
import com.example.agrican.domain.use_case.GetOrdersUseCase
import com.example.agrican.domain.use_case.GetPest
import com.example.agrican.domain.use_case.GetPests
import com.example.agrican.domain.use_case.GetTreatmentsUseCase
import com.example.agrican.domain.use_case.GetWeatherUseCase
import com.example.agrican.domain.use_case.JoinUsUseCase
import com.example.agrican.domain.use_case.OrderNewProductUseCase
import com.example.agrican.domain.use_case.SearchUseCase
import com.example.agrican.domain.use_case.SendMessageUseCase
import com.example.agrican.domain.use_case.auth.ForgotPasswordUseCase
import com.example.agrican.domain.use_case.auth.LoginUseCase
import com.example.agrican.domain.use_case.auth.SignOutUseCase
import com.example.agrican.domain.use_case.auth.SignupUseCase
import com.example.agrican.domain.use_case.validation.ValidateEmail
import com.example.agrican.domain.use_case.validation.ValidatePassword
import com.example.agrican.domain.use_case.validation.ValidatePhoneNumber
import com.example.agrican.domain.use_case.validation.ValidateRepeatedPassword
import com.example.agrican.domain.use_case.validation.ValidateUserName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(
        accountService: AccountService,
        mainRepository: MainRepository,
    ): BaseUseCase {
        return BaseUseCase(
            forgotPasswordUseCase = ForgotPasswordUseCase(accountService),
            loginUseCase = LoginUseCase(accountService),
            signOutUseCase = SignOutUseCase(accountService),
            signupUseCase = SignupUseCase(accountService, mainRepository),

            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            validatePhoneNumber = ValidatePhoneNumber(),
            validateRepeatedPassword = ValidateRepeatedPassword(),
            validateUserName = ValidateUserName(),

            getCurrentUserUseCase = GetCurrentUserUseCase(accountService, mainRepository),

            getWeatherUseCase = GetWeatherUseCase(mainRepository),
            getNewsUseCase = GetNewsUseCase(mainRepository),
            searchUseCase = SearchUseCase(mainRepository),
            calculateFertilizersUseCase = CalculateFertilizersUseCase(mainRepository),
            addTaskUseCase = AddTaskUseCase(mainRepository),

            getChatUseCase = GetChatUseCase(mainRepository),
            sendMessageUseCase = SendMessageUseCase(mainRepository),

            getCropsUseCase = GetCropsUseCase(mainRepository),
            getCropUseCase = GetCropUseCase(mainRepository),
            addCropUseCase = AddCropUseCase(mainRepository),

            getFarmsUseCase = GetFarmsUseCase(mainRepository),
            addFarmUseCase = AddFarmUseCase(mainRepository),

            getDefaultAgeUseCase = GetDefaultAgeUseCase(mainRepository),
            getOrdersUseCase = GetOrdersUseCase(mainRepository),
            getOrderUseCase = GetOrderUseCase(mainRepository),
            orderNewProductUseCase = OrderNewProductUseCase(mainRepository),
            getTreatmentsUseCase = GetTreatmentsUseCase(mainRepository),
            joinUsUseCase = JoinUsUseCase(mainRepository),

            getDisease = GetDisease(mainRepository),
            getDiseases = GetDiseases(mainRepository),
            getPest = GetPest(mainRepository),
            getPests = GetPests(mainRepository),
        )
    }
}