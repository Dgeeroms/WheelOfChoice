package com.natappsone.wheelofchoice.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natappsone.wheelofchoice.models.Wheel

@Dao
interface WheelsDatabaseDao{

    @Insert
    fun insert(wheel: Wheel) : Long

    @Update
    fun update(wheel: Wheel)

    @Query("SELECT * FROM wheel_table ORDER BY wheel_name ASC")
    fun getAll(): LiveData<List<Wheel>> //List<Wheel>

    @Delete
    fun delete(wheel: Wheel)

}