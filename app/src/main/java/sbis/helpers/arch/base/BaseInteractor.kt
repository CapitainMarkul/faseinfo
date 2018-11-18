package sbis.helpers.arch.base

import sbis.helpers.arch.contracts.MvpInteractor

abstract class BaseInteractor<L : MvpInteractor.Listener> : MvpInteractor<L> {

    override var listener: L? = null

    override fun destroy() {
//        todo: cancel all Coroutines
    }
}