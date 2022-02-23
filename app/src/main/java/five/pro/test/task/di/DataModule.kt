package five.pro.test.task.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import five.pro.test.task.room_data_base.Dao
import five.pro.test.task.room_data_base.RoomUsersViewModel
import five.pro.test.task.room_data_base.UsersDatabase
import five.pro.test.task.room_data_base.UsersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun getUserDatabase(@ApplicationContext context: Context): UsersDatabase{
        return UsersDatabase.getDatabase(context = context)
    }
    @Provides
    @Singleton
    fun getUseRepository(dao: Dao): UsersRepository{
        return UsersRepository(dao)
    }
    @Provides
    @Singleton
    fun getDao(usersDatabase: UsersDatabase): Dao{
        return usersDatabase.userDao()
    }
    //    private val database by lazy { UsersDatabase.getDatabase(this) }
//    val repository by lazy { UsersRepository(database.userDao()) }
}

@Module
@InstallIn(ViewModelComponent::class)
class DataViewModels {
    @Provides
    fun getUserViewModels(usersRepository: UsersRepository) : RoomUsersViewModel{
        return RoomUsersViewModel(repository = usersRepository)
    }
}