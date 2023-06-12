package com.example.agrican.data.repository

import android.util.Log
import com.amplifyframework.auth.AuthCategory
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.example.agrican.domain.repository.AccountService
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth: AuthCategory
): AccountService {
    override suspend fun login(userName: String, password: String) {
        auth.signIn(userName, password,
            { result ->
                if (result.isSignedIn) {
                    Log.i("AuthQuickstart", "Sign in succeeded")
                } else {
                    Log.i("AuthQuickstart", "Sign in not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to sign in", it) }
        )
    }

    override suspend fun signup(userName: String, email: String, password: String) {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()
        auth.signUp(userName, password, options,
            { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
            { Log.e("AuthQuickStart", "Sign up failed", it) }
        )
    }

    override suspend fun confirmSignUp(userName: String, code: String) {
        auth.confirmSignUp(
            userName, code,
            { result ->
                if (result.isSignUpComplete) {
                    Log.i("AuthQuickstart", "Confirm signUp succeeded")
                } else {
                    Log.i("AuthQuickstart", "Confirm sign up not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
        )
    }

    override suspend fun signOut() {
        auth.signOut { signOutResult ->
            when (signOutResult) {
                is AWSCognitoAuthSignOutResult.CompleteSignOut -> {
                    // Sign Out completed fully and without errors.
                    Log.i("AuthQuickStart", "Signed out successfully")
                }

                is AWSCognitoAuthSignOutResult.PartialSignOut -> {
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
                    // Sign Out failed with an exception, leaving the user signed in.
                    Log.e("AuthQuickStart", "Sign out Failed", signOutResult.exception)
                }
            }
        }
    }

    override suspend fun resetPassword(userName: String) {
        auth.resetPassword(userName,
            { Log.i("AuthQuickstart", "Password reset OK: $it") },
            { Log.e("AuthQuickstart", "Password reset failed", it) }
        )
    }

    override suspend fun confirmResetPassword(
        userName: String,
        newPassword: String,
        confirmationCode: String
    ) {
        auth.confirmResetPassword(userName, newPassword, confirmationCode,
            { Log.i("AuthQuickstart", "New password confirmed") },
            { Log.e("AuthQuickstart", "Failed to confirm password reset", it) }
        )
    }

    override suspend fun getCurrentUser(navigateToSignIn: () -> Unit) {
        auth.fetchAuthSession(
            {
                Log.i("AmplifyQuickstart", "Auth session = $it")
                if (!it.isSignedIn) {
                    navigateToSignIn()
                }
            },
            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
        )
    }
}