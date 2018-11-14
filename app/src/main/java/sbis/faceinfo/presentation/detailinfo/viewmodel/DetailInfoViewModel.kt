package sbis.faceinfo.presentation.detailinfo.viewmodel

import android.arch.lifecycle.MutableLiveData
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoVmContract
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoVmContract.ViewModel.State
import sbis.helpers.arch.base.BaseViewModel
import java.util.*

class DetailInfoViewModel(
    override val userId: UUID
) : BaseViewModel(), DetailInfoVmContract.ViewModel {
    override var state = MutableLiveData<State>().apply { value = State.INITIAL }
}