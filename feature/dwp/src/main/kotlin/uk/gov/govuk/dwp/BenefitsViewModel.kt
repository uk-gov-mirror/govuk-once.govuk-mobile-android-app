package uk.gov.govuk.dwp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.gov.govuk.dwp.data.BenefitsRepo
import java.security.SecureRandom
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
internal class BenefitsViewModel @Inject constructor(
    private val benefitsRepo: BenefitsRepo
): ViewModel() {

//    TODO: Analytics

    init {
        viewModelScope.launch {
            val bytes = ByteArray(32)
            SecureRandom().nextBytes(bytes)
            val guid = bytes.toHexString()
            println(guid)

            benefitsRepo.getBio(guid)
            benefitsRepo.getAwardDetails(guid)
        }
    }
}
