package sbis.domain.network.command

interface RpcCommand<T> {
    fun execute() : T
}