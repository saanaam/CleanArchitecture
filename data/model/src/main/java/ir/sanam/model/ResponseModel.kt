package ir.sanam.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(@SerializedName ("Code")  val code : Int? = null, @SerializedName ("Message") val message: String? = null)

