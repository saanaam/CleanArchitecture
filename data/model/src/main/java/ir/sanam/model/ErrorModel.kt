package ir.sanam.model


/**
 * Created by SM3 on 12/18/2017.
 */

data class ErrorModel(
    val type: Type = Type.UNEXPECTED,
    val message: String? = null,
    val localMessage: String? = null,
    val code: Int? = null,
    val response: ResponseModel? = null
) {
    enum class Type {
        HTTP,
        NETWORK,
        UNEXPECTED
    }
}
