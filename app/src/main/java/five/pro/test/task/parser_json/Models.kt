package five.pro.test.task.parser_json

interface BodyJsonProtocol  // type body request
interface ModelJsonProtocol // type json model



// body model sent for authorization
data class AuthorizationModel (
    var phone_code : String? = null,
    var phone_number : String? = null,
    var password : String? = null ) : BodyJsonProtocol



// for receiving data from the server after authorization
data class AuthorizationGetDataUser (
    var status : String? = null,
    var user : DataUser? = null ) : ModelJsonProtocol

data class DataUser (
    var user_id: Int? = null,
    var phone_code: String? = null,
    var phone_number: String? = null,
    var name: String? = null,
    var second_name: String? = null ) : ModelJsonProtocol