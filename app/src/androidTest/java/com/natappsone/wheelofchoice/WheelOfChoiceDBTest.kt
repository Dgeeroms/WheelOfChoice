package com.natappsone.wheelofchoice

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.database.WheelsDatabaseDao
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.models.WheelOption

import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WheelOfChoiceDBTest {

    private lateinit var wheelDao: WheelsDatabaseDao
    private lateinit var db: WheelsDatabase
    private lateinit var wheel: Wheel

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase("wheel_of_choice_database")
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        /*
        db = Room.inMemoryDatabaseBuilder(context, WheelsDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()

         */
        db = WheelsDatabase.getInstance(context)

        wheelDao = db.wDao
    }

    @Before
    fun createWheelObj(){

        wheel = Wheel()
        wheel.wheelName = "Test"

        val wheelOpt1 = WheelOption()
        wheelOpt1.wheelOptionName = "TestOption1"
        wheelOpt1.wheelOptionColor = "#ff0000"

        val wheelOpt2 = WheelOption()
        wheelOpt2.wheelOptionName = "TestOption2"
        wheelOpt2.wheelOptionColor = "#0004ff"

        wheel.wheelOptions = mutableListOf(wheelOpt1, wheelOpt2)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        //db.close() // Cannot perform this operation because the connection pool has been closed. ????
    }
    @Test
    @Throws(Exception::class)
    fun insertAndGetWheel() {
        wheelDao.insert(wheel)
        val wheels = wheelDao.getAll()

        assertEquals("Test", wheels[0].wheelName)
    }
    @Test
    @Throws(Exception::class)
    fun getWheelOption1Name() {
        wheelDao.insert(wheel)
        val wheels = wheelDao.getAll()
        assertEquals("TestOption1", wheels[0].wheelOptions[0].wheelOptionName)
    }
    @Test
    @Throws(Exception::class)
    fun getWheelOption1Color() {
        wheelDao.insert(wheel)
        val wheels = wheelDao.getAll()
        assertEquals("#ff0000", wheels[0].wheelOptions[0].wheelOptionColor)
    }
    @Test
    @Throws(Exception::class)
    fun getWheelOption2Name() {
        wheelDao.insert(wheel)
        val wheels = wheelDao.getAll()
        assertEquals("TestOption2", wheels[0].wheelOptions[1].wheelOptionName)
    }
    @Test
    @Throws(Exception::class)
    fun getWheelOption2Color() {
        wheelDao.insert(wheel)
        val wheels = wheelDao.getAll()
        assertEquals("#0004ff", wheels[0].wheelOptions[1].wheelOptionColor)
    }


}