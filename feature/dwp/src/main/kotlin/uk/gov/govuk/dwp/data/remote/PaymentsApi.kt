package uk.gov.govuk.dwp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uk.gov.govuk.dwp.data.remote.model.AwardDetail

interface PaymentsApi {
//    TODO: Add missing endpoints

    @GET("payment-aggregator/{guid}/award-details")
    suspend fun awardDetails(
        @Path("guid") guid: String
    ): Response<AwardDetail>
}
