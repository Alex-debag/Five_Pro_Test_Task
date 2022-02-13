package five.pro.test.task.room_data_base

import android.content.Context
import androidx.room.*

@Database(entities = [ModelDataUser::class], version = 1, exportSchema = false )
abstract class UsersDatabase : RoomDatabase() {
// get object type dao
    abstract fun userDao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: UsersDatabase? = null
// get instance database
        fun getDatabase(context: Context): UsersDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}