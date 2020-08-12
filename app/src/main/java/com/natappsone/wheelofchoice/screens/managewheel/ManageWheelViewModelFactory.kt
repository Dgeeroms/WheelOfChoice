package com.natappsone.wheelofchoice.screens.managewheel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.natappsone.wheelofchoice.database.WheelsDatabaseDao

class ManageWheelViewModelFactory (
    private val dataSource: WheelsDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManageWheelViewModel::class.java)) {
            return ManageWheelViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}