package pe.edu.ulima.pm.pokemonanderroger.model

import androidx.room.*
import java.util.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@Entity
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    var id_auto:Int?,
    @ColumnInfo ( name = "id")
    var id:String?,
    @ColumnInfo ( name = "name")
    var name :String?,
    @ColumnInfo ( name = "url")
    var url:String?,
    @ColumnInfo ( name = "hp")
    var hp:String?,
    @ColumnInfo ( name = "attack")
    var attack:String?,
    @ColumnInfo ( name = "defense")
    var defense:String?,
    @ColumnInfo ( name = "special_attack")
    var special_attack :String?,
    @ColumnInfo ( name = "special_defense")
    var special_defense:String?,


    @Ignore
    var stats:List<Habilidades>?,
    @Ignore
    var sprites:SpriteAtributes?

) { constructor(): this(null,null,null,null,null,null,null,null,null,null,null)
}

/*object Converters {
   /* @TypeConverter
    fun listToJson(value: List<Habilidades>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Habilidades>::class.java)?.toList()

    @TypeConverter
    fun ObjectToString(value: SpriteAtributes) = Gson().toJson(value)

   @TypeConverter
    fun StringToObject(value: String) = Gson().fromJson(value, SpriteAtributes::class.java)*/



   /* @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }*/
}*/

