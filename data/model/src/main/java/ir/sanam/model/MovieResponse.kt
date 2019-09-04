package ir.sanam.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search")
    @Expose
    var movieItems : List<MovieItems>? = null,

    @SerializedName("totalResults")
    var totalResults: String? = null,

    @SerializedName("Response")
    var response : String? = null
)