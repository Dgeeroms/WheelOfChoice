package com.natappsone.wheelofchoice.screens.managewheel

import android.util.Log
import androidx.lifecycle.ViewModel

class ManageWheelViewModel : ViewModel(){

    init {
        Log.i("ManageWheelViewModel", "ManageWheelViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ManageWheelViewModel", "ManageWheelViewModel destroyed!")
    }
}