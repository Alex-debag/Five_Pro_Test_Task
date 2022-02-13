package five.pro.test.task.room_data_base

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UsersRepository(private val dao: Dao) {
    // get all users
    val allUsers: Flow<List<ModelDataUser>> = dao.getAllUsers()

    // add user in database
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(usersData: ModelDataUser) {
        dao.insertDataUser(usersData)
    }
}