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

            createWheels()

        }
    }

    private suspend fun createWheels() {
        withContext(Dispatchers.IO){

            deleteAllWheels()

            var wheel = Wheel()
            wheel.wheelName = "Fast Food"
            wheel.wheelOptions = mutableListOf<WheelOption>()
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Pizza", wheelOptionColor = "#fc8403"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Chinese", wheelOptionColor = "#fc2003"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Fries", wheelOptionColor = "#0324fc"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Pitta", wheelOptionColor = "#446c82"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "McDonalds", wheelOptionColor = "#7703fc"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "VeggieStuff", wheelOptionColor = "#3dfc03"))
            insertWheel(wheel)

            wheel = Wheel()
            wheel.wheelName = "Yes or No"
            wheel.wheelOptions = mutableListOf<WheelOption>()
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "YES", wheelOptionColor = "#3dfc03"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "NO", wheelOptionColor = "#fc0303"))
            insertWheel(wheel)

            wheel = Wheel()
            wheel.wheelName = "Europe"
            wheel.wheelOptions = mutableListOf<WheelOption>()
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Albania", wheelOptionColor = "#fc0303"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Andorra", wheelOptionColor = "#0367fc"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Armenia", wheelOptionColor = "#fc6203"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Austria", wheelOptionColor = "#ffffff"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Azerbaijan", wheelOptionColor = "#00fff7"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Belarus", wheelOptionColor = "#2d7500"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Belgium", wheelOptionColor = "#000000"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Bosnia", wheelOptionColor = "#031285"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Bulgaria", wheelOptionColor = "#008a63"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Croatia", wheelOptionColor = "#c20404"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Netherlands", wheelOptionColor = "#ff7700"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Germany", wheelOptionColor = "#919191"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "France", wheelOptionColor = "#003f9e"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "Italy", wheelOptionColor = "#198705"))
            wheel.wheelOptions.add(WheelOption(wheelOptionName = "England", wheelOptionColor = "#570587"))
            insertWheel(wheel)

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