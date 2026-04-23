package com.ohyooo.lib.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.ohyooo.lib.extension.bindBaseLiveData

class MVVMViewModelFactory(private val owner: Any) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val viewModel = modelClass.getDeclaredConstructor().newInstance()
        if (viewModel is MVVMBaseViewModel) {
            when (owner) {
                is MVVMBaseActivity -> {
                    owner.lifecycle.addObserver(viewModel)
                    owner.bindBaseLiveData(viewModel)
                    viewModel.bundle = owner.intent.extras ?: Bundle()
                }
                is MVVMBaseFragment -> {
                    owner.lifecycle.addObserver(viewModel)
                    owner.bindBaseLiveData(viewModel)
                    viewModel.bundle = owner.arguments ?: Bundle()
                }
                is ComponentActivity -> owner.lifecycle.addObserver(viewModel)
                is Fragment -> owner.lifecycle.addObserver(viewModel)
            }
        }
        return viewModel
    }
}
