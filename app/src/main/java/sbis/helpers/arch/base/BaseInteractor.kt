package sbis.helpers.arch.base

import sbis.helpers.arch.contracts.MvpInteractor

abstract class BaseInteractor<L : MvpInteractor.Listener> : MvpInteractor<L> {

    override lateinit var listener: L

    override fun destroy() {
//        todo: cancel all Coroutines
    }
}