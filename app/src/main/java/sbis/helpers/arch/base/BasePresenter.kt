package sbis.helpers.arch.base

import sbis.helpers.arch.contracts.AndroidComponent
import sbis.helpers.arch.contracts.MvpPresenter
import sbis.helpers.arch.contracts.MvpViewModel

abstract class BasePresenter<VM : MvpViewModel> : MvpPresenter<VM> {

    override lateinit var viewModel: VM
    private var component: AndroidComponent? = null

    override fun attachView(viewModel: VM, component: AndroidComponent) {
        this.viewModel = viewModel
        this.component = component
    }

    override fun detachView() {
        component = null
    }

    override fun isAttachedView() = component != null

    override fun destroy() {
    }

    protected fun getAndroidComponent() = component
}