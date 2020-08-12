package com.natappsone.wheelofchoice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.models.WheelOption
import com.natappsone.wheelofchoice.utilities.converters.WheelOptionsConverter

@Database(entities = [Wheel::class/*, WheelOption::class*/], version = 2, exportSchema = false)
@TypeConverters(WheelOptionsConverter::class)
abstract class WheelsDatabase : RoomDatabase(){

    abstract val wDao: WheelsDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: WheelsDatabase? = null

        fun getInstance(context: Context) : WheelsDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext, WheelsDatabase::class.java, "wheel_of_choice_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}