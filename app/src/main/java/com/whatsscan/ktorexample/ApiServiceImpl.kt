package com.whatsscan.ktorexample

import com.whatsscan.ktorexample.models.RequestModel
import com.whatsscan.ktorexample.models.ResponseModel
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getProducts(): List<ResponseModel> {
        return try {
            client.get { url(ApiRoutes.PRODUCTS) }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            emptyList()
        }
    }

    override suspend fun createProducts(productRequest: RequestModel): ResponseModel? {
        return try {
            client.post<ResponseModel> {
                url(ApiRoutes.PRODUCTS)
                body = productRequest
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            null
        }
    }
}