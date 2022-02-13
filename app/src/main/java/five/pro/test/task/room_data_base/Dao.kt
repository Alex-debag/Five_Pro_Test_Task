package five.pro.test.task.room_data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataUser(dataUser: ModelDataUser)

    @Query("SELECT * FROM data_table")
    fun getAllUsers(): Flow<List<ModelDataUser>>

}