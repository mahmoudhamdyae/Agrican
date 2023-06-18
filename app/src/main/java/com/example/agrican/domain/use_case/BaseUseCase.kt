package com.example.agrican.domain.use_case

import com.example.agrican.domain.use_case.auth.ConfirmResetPasswordUseCase
import com.example.agrican.domain.use_case.auth.ConfirmSignUpUseCase
import com.example.agrican.domain.use_case.auth.ForgotPasswordUseCase
import com.example.agrican.domain.use_case.auth.LoginUseCase
import com.example.agrican.domain.use_case.auth.SignOutUseCase
import com.example.agrican.domain.use_case.auth.SignupUseCase
import com.example.agrican.domain.use_case.validation.ValidateConfirmCodeCode
import com.example.agrican.domain.use_case.validation.ValidateEmail
import com.example.agrican.domain.use_case.validation.ValidatePassword
import com.example.agrican.domain.use_case.validation.ValidatePhoneNumber
import com.example.agrican.domain.use_case.validation.ValidateRepeatedPassword
import com.example.agrican.domain.use_case.validation.ValidateUserName

data class BaseUseCase (
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val loginUseCase: LoginUseCase,
    val signOutUseCase: SignOutUseCase,
    val signupUseCase: SignupUseCase,
    val confirmSignUpUseCase: ConfirmSignUpUseCase,
    val confirmResetPasswordUseCase: ConfirmResetPasswordUseCase,

    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validatePhoneNumber: ValidatePhoneNumber,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateUserName: ValidateUserName,
    val validateConfirmCodeCode: ValidateConfirmCodeCode,

    val getCurrentUserUseCase: GetCurrentUserUseCase,

    val getWeatherUseCase: GetWeatherUseCase,
    val getNewsUseCase: GetNewsUseCase,
    val searchUseCase: SearchUseCase,
    val calculateFertilizersUseCase: CalculateFertilizersUseCase,
    val addTaskUseCase: AddTaskUseCase,

    val getChatUseCase: GetChatUseCase,
    val sendMessageUseCase: SendMessageUseCase,

    val getCropsUseCase: GetCropsUseCase,
    val getCropUseCase: GetCropUseCase,
    val addCropUseCase: AddCropUseCase,

    val getFarmsUseCase: GetFarmsUseCase,
    val addFarmUseCase: AddFarmUseCase,

    val getDefaultAgeUseCase: GetDefaultAgeUseCase,
    val getOrdersUseCase: GetOrdersUseCase,
    val getOrderUseCase: GetOrderUseCase,
    val orderNewProductUseCase: OrderNewProductUseCase,
    val getTreatmentsUseCase: GetTreatmentsUseCase,
    val joinUsUseCase: JoinUsUseCase,

    val getDisease: GetDisease,
    val getDiseases: GetDiseases,
    val getPest: GetPest,
    val getPests: GetPests,
)