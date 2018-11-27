package sbis.faceinfo.presentation.detailinfo.viewmodel

import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoVmContract
import sbis.helpers.arch.base.BaseViewModel

class DetailInfoViewModel(
    override val userId: String
) : BaseViewModel(), DetailInfoVmContract.ViewModel {

    // TODO: state, errorMessage, userFullInfo
}