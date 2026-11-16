package uk.gov.govuk.dwp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uk.gov.govuk.dwp.data.remote.model.Bio

interface PersonalApi {
//    TODO: Add missing endpoints

    @GET("personal-aggregator/{guid}/core-bio")
    suspend fun bio(
        @Path("guid") guid: String
    ): Response<Bio>
}
