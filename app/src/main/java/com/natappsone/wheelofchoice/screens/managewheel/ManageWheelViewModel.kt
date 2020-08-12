package com.natappsone.wheelofchoice.screens.managewheel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.database.WheelsDatabaseDao
import com.natappsone.wheelofchoice.models.Wheel
import kotlinx.coroutines.*

class ManageWheelViewModel(val db: WheelsDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var wheels = MutableLiveData<List<Wheel>>()

    init {
        initializeWheels()
    }

    private fun initializeWheels() {
        uiScope.launch {
            wheels.value = getWheelsFromDatabase()
        }
    }

    private suspend fun getWheelsFromDatabase(): List<Wheel>? {
        return withContext(Dispatchers.IO){
            var wheels = db.getAll()
            wheels.value
        }
    }

    private fun insertWheel(wheel: Wheel){
        uiScope.launch {
            insert(wheel)
            wheels.value = getWheelsFromDatabase()
        }
    }

    private suspend fun insert(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.insert(wheel)
        }
    }

    private fun updateWheel(wheel: Wheel){
        uiScope.launch {
            update(wheel)
            wheels.value = getWheelsFromDatabase()
        }
    }

    private suspend fun update(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.update(wheel)
        }
    }

    private fun deleteWheel(wheel: Wheel){
        uiScope.launch {
            delete(wheel)
            wheels.value = getWheelsFromDatabase()
        }
    }

    private suspend fun delete(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.delete(wheel)
        }
    }


}