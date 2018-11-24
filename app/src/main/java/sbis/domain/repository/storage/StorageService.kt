package sbis.domain.repository.storage

interface StorageService {

    fun saveServerUrl(serverUrl: String)

    fun getServerUrl(): String
    fun getFullServerUrl(): String
}