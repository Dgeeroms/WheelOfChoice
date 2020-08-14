package com.natappsone.wheelofchoice.screens.managewheel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.database.WheelsDatabaseDao
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.models.WheelOption
import kotlinx.coroutines.*

class ManageWheelViewModel(val db: WheelsDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val wheels = db.getAll()


    private var _navigateToUpdateWheel = MutableLiveData<Wheel>()

    val navigateToUpdateWheel : LiveData<Wheel>
        get() = _navigateToUpdateWheel

    fun doneNavigating(){
        _navigateToUpdateWheel.value = null
    }

    private var _showSavedSnack = MutableLiveData<Boolean>()
    val showSavedSnack : LiveData<Boolean>
        get() = _showSavedSnack

    fun _doneShowingSavedSnack(){
        _showSavedSnack.value = false
    }

    init {
        initializeWheels()
    }

    private fun initializeWheels() {
        uiScope.launch {

            //deleteAllWheels()

            //some sample data
            var wheel = Wheel()
            wheel.wheelName = "Test"

            val wheelOpt1 = WheelOption()
            wheelOpt1.wheelOptionName = "TestOption1"
            wheelOpt1.wheelOptionColor = "#ff0000"
            wheel.wheelOptions = mutableListOf(wheelOpt1, wheelOpt1)

            insertWheel(wheel)

            //wheels = db.getAll()
        }
    }

    private suspend fun deleteAllWheels() {
        withContext(Dispatchers.IO){
            db.deleteAll()
        }
    }

    /*
        private suspend fun getWheelsFromDatabase(): List<Wheel>? {
            return withContext(Dispatchers.IO){
                var wheels = db.getAll()
                wheels.value
            }
        }
    */
    private fun insertWheel(wheel: Wheel){
        uiScope.launch {
            insert(wheel)
           // wheels.value = getWheelsFromDatabase()
        }
    }

    private suspend fun insert(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.insert(wheel)
        }
    }

    fun goToUpdateWheel(wheelId: Long){
        uiScope.launch {
            _navigateToUpdateWheel.value = getById(wheelId)
        }
    }

    private suspend fun getById(wheelId: Long): Wheel{
        var wheel = Wheel()
        withContext(Dispatchers.IO){
           wheel = db.getById(wheelId)
        }
        return wheel
    }

    private fun updateWheel(wheel: Wheel){
        uiScope.launch {
            update(wheel)
            _navigateToUpdateWheel.value = wheel
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
           // wheels.value = getWheelsFromDatabase()
        }
    }

    private suspend fun delete(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.delete(wheel)
        }
    }

    fun onSave(){
        //TODO get Wheel and insert or update
        _showSavedSnack.value = true
    }


}