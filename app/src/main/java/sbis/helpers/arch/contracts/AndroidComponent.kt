package sbis.helpers.arch.contracts

import android.app.Activity
import android.support.v4.app.FragmentManager

interface AndroidComponent {
    fun getComponentActivity(): Activity
    fun getComponentFragmentManager(): FragmentManager
}