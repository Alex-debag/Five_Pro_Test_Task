package five.pro.test.task.room_data_base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
class ModelDataUser(@PrimaryKey
                    @ColumnInfo (name = "id") val user_id: Int,
                    @ColumnInfo val phone_number: String,
                    @ColumnInfo val name: String,
                    @ColumnInfo val second_name: String )