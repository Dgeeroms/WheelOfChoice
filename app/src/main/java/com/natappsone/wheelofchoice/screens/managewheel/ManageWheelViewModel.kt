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

    private var _wheelToUpdate = MutableLiveData<Wheel>()
    val wheelToUpdate: LiveData<Wheel>
        get() = _wheelToUpdate

    private var _randomWheelOption = MutableLiveData<Int>()
    val randomWheelOption: LiveData<Int>
        get() = _randomWheelOption

    private var _showSavedSnack = MutableLiveData<Boolean>()
    val showSavedSnack : LiveData<Boolean>
        get() = _showSavedSnack

    fun _doneShowingSavedSnack(){
        _showSavedSnack.value = false
    }

    init {
        initializeWheels()
    }

    fun spin(wheelCount : Int){
        _randomWheelOption.value = (0..wheelCount-1).random()
    }


    fun updateCurrentWheel(wheelId: Long)
    {
        uiScope.launch {
            _wheelToUpdate.value = getById(wheelId)
        }

    }

    fun changeOptionColor(wheelOption: WheelOption){

        var index = _wheelToUpdate.value?.wheelOptions?.indexOf(wheelOption)
        if (index != null) {
            _wheelToUpdate.value?.wheelOptions?.set(index, wheelOption)
        }
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
            val wheelOpt2 = WheelOption()
            wheelOpt2.wheelOptionName = "TestOption2"
            wheelOpt2.wheelOptionColor = "#ffffff"
            wheel.wheelOptions = mutableListOf(wheelOpt1, wheelOpt2)

            //insertWheel(wheel)
        }
    }

    private suspend fun deleteAllWheels() {
        withContext(Dispatchers.IO){
            db.deleteAll()
        }
    }

    private fun insertWheel(wheel: Wheel){
        uiScope.launch {
            insert(wheel)
        }
    }

    private suspend fun insert(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.insert(wheel)
        }
    }

    fun goToUpdateWheel(wheelId: Long){
        uiScope.launch {
            val wheel = getById(wheelId)
            _navigateToUpdateWheel.value = wheel
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
        }
    }

    private suspend fun delete(wheel: Wheel) {
        withContext(Dispatchers.IO){
            db.delete(wheel)
        }
    }


    fun onUpdate(wheel: Wheel){
        uiScope.launch {
            _wheelToUpdate.value = wheel
            update(wheel)
        }
        _showSavedSnack.value = true
    }

    fun onInsert(wheel: Wheel){
        uiScope.launch {
            //_wheelToUpdate.value = wheel
            insert(wheel)
        }
        _showSavedSnack.value = true
    }

}