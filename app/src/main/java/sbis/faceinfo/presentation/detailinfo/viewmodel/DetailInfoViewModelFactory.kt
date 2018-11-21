package sbis.faceinfo.presentation.detailinfo.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class DetailInfoViewModelFactory(private val userId: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == DetailInfoViewModel::class.java) {
            DetailInfoViewModel(userId) as T
        } else super.create(modelClass)
    }
}