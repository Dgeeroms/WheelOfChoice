package com.natappsone.wheelofchoice.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    @Query("SELECT * FROM wheel_table WHERE wheelId = :wheelId")
    fun getById(wheelId: Long): Wheel

    @Delete
    fun delete(wheel: Wheel)

    @Query("DELETE FROM wheel_table")
    fun deleteAll()

}