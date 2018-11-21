package sbis.faceinfo.presentation.detailinfo.presenter

import sbis.data.model.presentation.PersonFullInfo
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoInteractorContract
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoRouterContract
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoVmContract
import sbis.helpers.arch.base.BasePresenter
import sbis.helpers.arch.contracts.AndroidComponent

class DetailInfoPresenter(
    val interactor: DetailInfoInteractorContract.Interactor,
    val router: DetailInfoRouterContract.Router
) : BasePresenter<DetailInfoVmContract.ViewModel>(),
    DetailInfoVmContract.Presenter,
    DetailInfoInteractorContract.Presenter,
    DetailInfoRouterContract.Presenter {

    override fun attachView(viewModel: DetailInfoVmContract.ViewModel, component: AndroidComponent) {
        super.attachView(viewModel, component)
        interactor.listener = this

        interactor.obtainUserFullInfo(viewModel.userId)
    }

    override fun detachView() {
        super.detachView()
    }

    override fun obtainedUserFulInfo(user: PersonFullInfo, error: Throwable?) {
        vm.user.value = user
    }
}