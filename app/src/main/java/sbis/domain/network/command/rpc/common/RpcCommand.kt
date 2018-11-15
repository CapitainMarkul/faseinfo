package sbis.domain.network.command.rpc.common

interface RpcCommand<T> {
    fun execute() : T
}