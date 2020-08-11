package com.natappsone.wheelofchoice.screens.managewheel

import android.arch.lifecycle.ViewModel
import android.util.Log

class ManageWheelViewModel : ViewModel(){

    init {
        Log.i("ManageWheelViewModel", "ManageWheelViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ManageWheelViewModel", "ManageWheelViewModel destroyed!")
    }
}