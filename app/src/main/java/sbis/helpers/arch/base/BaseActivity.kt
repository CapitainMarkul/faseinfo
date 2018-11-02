package sbis.helpers.arch.base

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import sbis.helpers.arch.contracts.AndroidComponent
import sbis.helpers.arch.PresenterStorage
import sbis.helpers.arch.contracts.MvpPresenter
import sbis.helpers.arch.contracts.MvpViewModel

abstract class BaseActivity<PRESENTER : MvpPresenter<VIEW_MODEL>, VIEW_MODEL : MvpViewModel>
    : AppCompatActivity(), AndroidComponent {

    companion object {
        const val VM_KEY = "VM_KEY"
    }

    lateinit var presenter: PRESENTER
    lateinit var viewModel: VIEW_MODEL

    abstract fun createPresenter(): PRESENTER
    abstract fun createViewModel(): VIEW_MODEL
    abstract fun createSubscribers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = createViewModel()
        createSubscribers()

        presenter =
                if (savedInstanceState == null) createPresenter()
                else PresenterStorage.instance.evict(viewModel.id)

        takeIf { presenter == null }?.apply { presenter = createPresenter() }
    }

    override fun onRestart() {
        super.onRestart()
        attachView()
    }

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onStop() {
        with(presenter) {
            detachView()

            if (!isChangingConfigurations) destroy()
            else PresenterStorage.instance.save(viewModel.id, this)
        }

        super.onStop()
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) presenter.destroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(VM_KEY, viewModel as Parcelable)

        super.onSaveInstanceState(outState)
    }

    override fun getComponentActivity(): Activity = this

    override fun getComponentFragmentManager(): FragmentManager = supportFragmentManager

    private fun attachView() {
        with(presenter) {
            if (!isAttachedView()) attachView(this@BaseActivity.viewModel, this@BaseActivity)
        }
    }
}