package com.theflankers.agrican.data.repository

import android.util.Log
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.kotlin.auth.KotlinAuthFacade
import com.theflankers.agrican.common.snackbar.SnackBarManager
import com.theflankers.agrican.common.snackbar.SnackBarMessage.Companion.toSnackBarMessage
import com.theflankers.agrican.domain.repository.AccountService
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth: KotlinAuthFacade
): AccountService {

    override suspend fun hasUser(): Boolean {
        return auth.fetchAuthSession().isSignedIn
    }

    override suspend fun getCurrentUserId(): String {
        return auth.getCurrentUser().userId
    }

    override suspend fun login(
        userName: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        try {
            val result = auth.signIn(userName, password)
            if (result.isSignedIn) {
                Log.i("AuthQuickstart", "Sign in succeeded")
                onSuccess()
            } else {
                Log.e("AuthQuickstart", "Sign in not complete")
                onError()
            }
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Sign in failed", error)
            SnackBarManager.showMessage(error.toSnackBarMessage())
            onError()
        }
    }

    override suspend fun signup(
        userName: String,
        email: String,
        phoneNumber: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()
        try {
            val result = auth.signUp(userName, password, options)
            Log.i("AuthQuickStart", "Result: $result")
            onSuccess()
        } catch (error: AuthException) {
            Log.e("AuthQuickStart", "Sign up failed", error)
            SnackBarManager.showMessage(error.toSnackBarMessage())
            onError()
        }
    }

    override suspend fun confirmSignUp(
        userName: String,
        code: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        try {
            val result = auth.confirmSignUp(userName, code)
            if (result.isSignUpComplete) {
                Log.i("AuthQuickstart", "Signup confirmed")
                login(userName, password, onSuccess, onError)
            } else {
                Log.i("AuthQuickstart", "Signup confirmation not yet complete")
                onError()
            }
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Failed to confirm signup", error)
            SnackBarManager.showMessage(error.toSnackBarMessage())
            onError()
        }
    }

    override suspend fun signOut(onSuccess: () -> Unit, onError: () -> Unit) {
        when(val signOutResult = auth.signOut()) {
            is AWSCognitoAuthSignOutResult.CompleteSignOut -> {
                // Sign Out completed fully and without errors.
                Log.i("AuthQuickStart", "Signed out successfully")
                onSuccess()
            }
            is AWSCognitoAuthSignOutResult.PartialSignOut -> {
                onError()

                // Sign Out completed with some errors. User is signed out of the device.
                signOutResult.hostedUIError?.let {
                    Log.e("AuthQuickStart", "HostedUI Error", it.exception)
                    // Optional: Re-launch it.url in a Custom tab to clear Cognito web session.

                }
                signOutResult.globalSignOutError?.let {
                    Log.e("AuthQuickStart", "GlobalSignOut Error", it.exception)
                    // Optional: Use escape hatch to retry revocation of it.accessToken.
                }
                signOutResult.revokeTokenError?.let {
                    Log.e("AuthQuickStart", "RevokeToken Error", it.exception)
                    // Optional: Use escape hatch to retry revocation of it.refreshToken.
                }
            }
            is AWSCognitoAuthSignOutResult.FailedSignOut -> {
                onError()

                // Sign Out failed with an exception, leaving the user signed in.
                Log.e("AuthQuickStart", "Sign out Failed", signOutResult.exception)
                SnackBarManager.showMessage(signOutResult.exception.toSnackBarMessage())
            }
        }
    }

    override suspend fun resetPassword(
        userName: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        try {
            val result = auth.resetPassword(userName)
            Log.i("AuthQuickstart", "Password reset OK: $result")
            onSuccess()
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Password reset failed", error)
            SnackBarManager.showMessage(error.toSnackBarMessage())
            onError()
        }
    }

    override suspend fun confirmResetPassword(
        userName: String,
        newPassword: String,
        confirmationCode: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        try {
            auth.confirmResetPassword(userName, newPassword, confirmationCode)
            Log.i("AuthQuickstart", "New password confirmed")
            login(userName, newPassword, onSuccess, onError)
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Failed to confirm password reset", error)
            SnackBarManager.showMessage(error.toSnackBarMessage())
            onError()
        }
    }
}