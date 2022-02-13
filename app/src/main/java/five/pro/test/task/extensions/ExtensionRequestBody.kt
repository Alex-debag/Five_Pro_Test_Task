package five.pro.test.task.extensions

import com.google.gson.Gson
import five.pro.test.task.parser_json.BodyJsonProtocol
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

// get RequestBody by set the desired data class
fun BodyJsonProtocol.getBody(): RequestBody {

    val json = Gson().toJson(this)

    return json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}