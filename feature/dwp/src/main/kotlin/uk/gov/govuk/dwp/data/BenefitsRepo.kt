package uk.gov.govuk.dwp.data

import uk.gov.govuk.data.auth.AuthRepo
import uk.gov.govuk.dwp.data.remote.BenefitsResult
import uk.gov.govuk.dwp.data.remote.PaymentsApi
import uk.gov.govuk.dwp.data.remote.PersonalApi
import uk.gov.govuk.dwp.data.remote.model.AwardDetail
import uk.gov.govuk.dwp.data.remote.model.Bio
import uk.gov.govuk.dwp.data.remote.safeBenefitsApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BenefitsRepo @Inject constructor(
    private val paymentsApi: PaymentsApi,
    private val personalApi: PersonalApi,
    private val authRepo: AuthRepo
) {
    suspend fun getBio(guid: String): BenefitsResult<Bio> {
        return safeBenefitsApiCall(
            apiCall = { personalApi.bio(guid) },
            authRepo = authRepo
        )
    }

    suspend fun getAwardDetails(guid: String): BenefitsResult<AwardDetail> {
        return safeBenefitsApiCall(
            apiCall = { paymentsApi.awardDetails(guid) },
            authRepo = authRepo
        )
    }
}
