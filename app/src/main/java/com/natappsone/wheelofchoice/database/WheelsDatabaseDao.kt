package com.natappsone.wheelofchoice.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natappsone.wheelofchoice.models.Wheel

@Dao
interface WheelsDatabaseDao{

    @Insert
    fun insert(wheel: Wheel)

    @Update
    fun update(wheel: Wheel)

    @Query("SELECT * FROM wheel_table ORDER BY wheelName ASC")
    fun getAll(): LiveData<List<Wheel>>

    @Delete
    fun delete(wheel: Wheel)

}