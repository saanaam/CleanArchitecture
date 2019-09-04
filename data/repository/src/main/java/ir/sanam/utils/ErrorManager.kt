package ir.sanam.utils



import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.sanam.model.ErrorModel
import ir.sanam.model.ResponseModel
import retrofit2.HttpException
import java.io.IOException


interface ErrorManager {
    fun errorModel(): ErrorModel
}

class RemoteErrorManager(val error: Throwable) : ErrorManager {
    override fun errorModel() =
        when (error) {
            is HttpException -> {
                val s = error.response().errorBody()?.string()
                try {
                    val model = Gson().fromJson<List<ResponseModel>>(
                        s,
                        object : TypeToken<List<ResponseModel>>() {
                        }.type
                    )
                    ErrorModel(ErrorModel.Type.HTTP, error.message(), null, error.code(), if (model == null || model.isEmpty()) null else model[0])
                }catch (e : Exception){
                    try {
                        val model = Gson().fromJson<ResponseModel>(
                            s,
                            object : TypeToken<ResponseModel>() {
                            }.type
                        )
                        ErrorModel(ErrorModel.Type.HTTP, error.message(), null, error.code(), if (model == null) null else model)
                    }catch (e : Exception) {


                        ErrorModel(ErrorModel.Type.HTTP, error.message(), null, error.code(), null)
                    }
                }
            }
            is IOException -> ErrorModel(ErrorModel.Type.NETWORK, error.message)
            else -> ErrorModel(ErrorModel.Type.UNEXPECTED, error.message)
        }


}