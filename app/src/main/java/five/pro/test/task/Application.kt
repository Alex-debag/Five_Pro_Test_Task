package five.pro.test.task

import android.app.Application
import five.pro.test.task.room_data_base.UsersDatabase
import five.pro.test.task.room_data_base.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ThisApplication : Application() {
    // create instances database and repository
    private val database by lazy { UsersDatabase.getDatabase(this) }
    val repository by lazy { UsersRepository(database.userDao()) }
}