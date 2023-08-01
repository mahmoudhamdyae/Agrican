package com.theflankers.agrican.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theflankers.agrican.common.snackbar.SnackBarManager
import com.theflankers.agrican.common.snackbar.SnackBarMessage.Companion.toSnackBarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
): ViewModel() {

    fun launchCatching(snackBar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackBar) {
                    viewModelScope.launch {
                        SnackBarManager.showMessage(throwable.toSnackBarMessage())
                    }
                }
            },
            block = block
        )
}