package com.example.retrofitlesson.Retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainAPI {
    @GET("products/{id}")
   suspend fun getProductByID(@Path("id") id : Int) : Product

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest) : User

    @GET("products")
    suspend fun getAllProducts() : Products

    @GET("products/search")
    suspend fun getProductsByName(@Query("q") name : String) : Products



}