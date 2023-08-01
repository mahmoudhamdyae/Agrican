package com.theflankers.agrican.di

import com.theflankers.agrican.domain.repository.AccountService
import com.theflankers.agrican.domain.repository.MainRepository
import com.theflankers.agrican.domain.repository.WeatherRepository
import com.theflankers.agrican.domain.use_case.AddCropUseCase
import com.theflankers.agrican.domain.use_case.AddFarmUseCase
import com.theflankers.agrican.domain.use_case.AddTaskUseCase
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.domain.use_case.CalculateFertilizersUseCase
import com.theflankers.agrican.domain.use_case.GetChatUseCase
import com.theflankers.agrican.domain.use_case.GetCropUseCase
import com.theflankers.agrican.domain.use_case.GetCropsUseCase
import com.theflankers.agrican.domain.use_case.GetCurrentUserUseCase
import com.theflankers.agrican.domain.use_case.GetDefaultAgeUseCase
import com.theflankers.agrican.domain.use_case.GetDiseaseUseCase
import com.theflankers.agrican.domain.use_case.GetDiseasesUseCase
import com.theflankers.agrican.domain.use_case.GetFarmsUseCase
import com.theflankers.agrican.domain.use_case.GetNewsUseCase
import com.theflankers.agrican.domain.use_case.GetOrderUseCase
import com.theflankers.agrican.domain.use_case.GetOrdersUseCase
import com.theflankers.agrican.domain.use_case.GetPestUseCase
import com.theflankers.agrican.domain.use_case.GetPestsUseCase
import com.theflankers.agrican.domain.use_case.GetTreatmentsUseCase
import com.theflankers.agrican.domain.use_case.GetWeatherUseCase
import com.theflankers.agrican.domain.use_case.JoinUsUseCase
import com.theflankers.agrican.domain.use_case.OrderNewProductUseCase
import com.theflankers.agrican.domain.use_case.SearchUseCase
import com.theflankers.agrican.domain.use_case.SendMessageUseCase
import com.theflankers.agrican.domain.use_case.auth.ConfirmResetPasswordUseCase
import com.theflankers.agrican.domain.use_case.auth.ConfirmSignUpUseCase
import com.theflankers.agrican.domain.use_case.auth.ForgotPasswordUseCase
import com.theflankers.agrican.domain.use_case.auth.LoginUseCase
import com.theflankers.agrican.domain.use_case.auth.SignOutUseCase
import com.theflankers.agrican.domain.use_case.auth.SignupUseCase
import com.theflankers.agrican.domain.use_case.validation.ValidateConfirmCodeCode
import com.theflankers.agrican.domain.use_case.validation.ValidateEmail
import com.theflankers.agrican.domain.use_case.validation.ValidatePassword
import com.theflankers.agrican.domain.use_case.validation.ValidatePhoneNumber
import com.theflankers.agrican.domain.use_case.validation.ValidateRepeatedPassword
import com.theflankers.agrican.domain.use_case.validation.ValidateUserName
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
        weatherRepository: WeatherRepository
    ): BaseUseCase {
        return BaseUseCase(
            forgotPasswordUseCase = ForgotPasswordUseCase(accountService),
            loginUseCase = LoginUseCase(accountService),
            signOutUseCase = SignOutUseCase(accountService),
            signupUseCase = SignupUseCase(accountService, mainRepository),
            confirmSignUpUseCase = ConfirmSignUpUseCase(accountService),
            confirmResetPasswordUseCase = ConfirmResetPasswordUseCase(accountService),

            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            validatePhoneNumber = ValidatePhoneNumber(),
            validateRepeatedPassword = ValidateRepeatedPassword(),
            validateUserName = ValidateUserName(),
            validateConfirmCodeCode = ValidateConfirmCodeCode(),

            getCurrentUserUseCase = GetCurrentUserUseCase(accountService, mainRepository),

            getWeatherUseCase = GetWeatherUseCase(weatherRepository),
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

            getDiseaseUseCase = GetDiseaseUseCase(mainRepository),
            getDiseasesUseCase = GetDiseasesUseCase(mainRepository),
            getPestUseCase = GetPestUseCase(mainRepository),
            getPestsUseCase = GetPestsUseCase(mainRepository),
        )
    }
}