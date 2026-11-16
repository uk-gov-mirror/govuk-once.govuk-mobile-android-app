package uk.gov.govuk.dwp.data.remote

import kotlinx.coroutines.CancellationException
import retrofit2.Response
import uk.gov.govuk.data.auth.AuthRepo
import uk.gov.govuk.data.remote.AuthenticationException
import uk.gov.govuk.data.remote.withAuthRetry

internal suspend fun <T> safeBenefitsApiCall(
    apiCall: suspend () -> Response<T>,
    authRepo: AuthRepo
): BenefitsResult<T> {
    return try {
        val response = withAuthRetry(apiCall, authRepo)
        val body = response.body()
        val code = response.code()

        println(body)

        when {
            response.isSuccessful -> {
                when {
                    body != null -> BenefitsResult.Success(body)
                    else -> BenefitsResult.Error()
                }
            }

            else -> {
                when {
                    code == 404 -> BenefitsResult.NotFound()
                    else -> BenefitsResult.Error()
                }
            }
        }
    } catch (e: CancellationException) {
        throw e
    } catch (e: AuthenticationException) {
        BenefitsResult.AuthError()
    } catch (e: Exception) {
        BenefitsResult.DeviceOffline()
    }
}
