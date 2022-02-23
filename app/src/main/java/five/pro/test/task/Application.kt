package five.pro.test.task

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//import five.pro.test.task.room_data_base.UsersDatabase
//import five.pro.test.task.room_data_base.UsersRepository
@HiltAndroidApp
class ThisApplication : Application()
//{
//    // create instances database and repository
//    private val database by lazy { UsersDatabase.getDatabase(this) }
//    val repository by lazy { UsersRepository(database.userDao()) }
//}