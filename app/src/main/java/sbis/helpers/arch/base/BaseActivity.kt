package sbis.helpers.arch.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import sbis.helpers.arch.PresenterStorage
import sbis.helpers.arch.contracts.AndroidComponent
import sbis.helpers.arch.contracts.MvpPresenter
import sbis.helpers.arch.contracts.MvpViewModel

abstract class BaseActivity<PRESENTER : MvpPresenter<VIEW_MODEL>, VIEW_MODEL : MvpViewModel>
    : AppCompatActivity(), AndroidComponent {

    lateinit var presenter: PRESENTER
    lateinit var viewModel: VIEW_MODEL

    override val activity: Activity
        get() = this@BaseActivity

    override val fragmentManager: FragmentManager
        get() = supportFragmentManager

    abstract fun createPresenter(): PRESENTER
    abstract fun createViewModel(): VIEW_MODEL
    abstract fun createSubscribers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = createViewModel()
        createSubscribers()

        presenter = createPresenter()
//                if (savedInstanceState == null) createPresenter()
//                else PresenterStorage.instance.evict(viewModel.id)

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
            else PresenterStorage.instance.save(vm.id, this)
        }

        super.onStop()
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) presenter.destroy()
        super.onDestroy()
    }

    private fun attachView() {
        with(presenter) {
            if (!isAttachedView()) attachView(this@BaseActivity.viewModel, this@BaseActivity)
        }
    }
}