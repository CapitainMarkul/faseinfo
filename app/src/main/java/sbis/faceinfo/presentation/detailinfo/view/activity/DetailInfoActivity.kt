package sbis.faceinfo.presentation.detailinfo.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.View
import android.view.animation.AlphaAnimation
import sbis.App
import sbis.faceinfo.R
import sbis.faceinfo.databinding.ActivityDetailInfoBinding
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoVmContract
import sbis.faceinfo.presentation.detailinfo.interactor.DetailInfoInteractor
import sbis.faceinfo.presentation.detailinfo.presenter.DetailInfoPresenter
import sbis.faceinfo.presentation.detailinfo.router.DetailInfoRouter
import sbis.faceinfo.presentation.detailinfo.view.adapter.DetailParamsAdapter
import sbis.faceinfo.presentation.detailinfo.viewmodel.DetailInfoViewModel
import sbis.faceinfo.presentation.detailinfo.viewmodel.DetailInfoViewModelFactory
import sbis.helpers.arch.base.BaseActivity

class DetailInfoActivity : BaseActivity<DetailInfoVmContract.Presenter, DetailInfoVmContract.ViewModel>(),
    AppBarLayout.OnOffsetChangedListener {

    companion object {
        private const val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.8f
        private const val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.1f
        private const val ALPHA_ANIMATIONS_DURATION = 200

        private val ARG_USER_ID = "ARG_USER_ID"

        fun createIntent(context: Context, userId: String): Intent =
            Intent(context, DetailInfoActivity::class.java).apply {
                putExtra(ARG_USER_ID, userId)
            }
    }

    private lateinit var binding: ActivityDetailInfoBinding
    private lateinit var detailParamsAdapter: DetailParamsAdapter

    private var isTitleVisible = false
    private var isTitleContainerVisible = true

    override fun createPresenter(): DetailInfoVmContract.Presenter =
        DetailInfoPresenter(
            DetailInfoInteractor(App.get().getNetworkService()),
            DetailInfoRouter()
        )

    override fun createViewModel(): DetailInfoVmContract.ViewModel {
        val userId = intent.extras.getString(ARG_USER_ID)
        return ViewModelProviders.of(this, DetailInfoViewModelFactory(userId))
            .get(DetailInfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@DetailInfoActivity, R.layout.activity_detail_info)
        binding.setLifecycleOwner(this@DetailInfoActivity)
        binding.viewModel = viewModel

        binding.appBar.addOnOffsetChangedListener(this)
        startAlphaAnimation(binding.toolbarUserName, 0, View.INVISIBLE)



        // TODO: create 'DetailParamsAdapter', set first items

        // TODO: set config RecyclerView (ParamItems)
    }

    override fun createSubscribers() {
        //TODO: observe errorMessage, userFullInfo
        //TODO: 'errorMessage' -> ShowErrorMessage
        //TODO: 'userFullInfo' -> detailParamsAdapter.setItems(...)
    }

    //region Animation
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()

        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR && !isTitleVisible) {
            startAlphaAnimation(binding.toolbarUserName, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
            isTitleVisible = true
        } else if (percentage < PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR && isTitleVisible) {
            startAlphaAnimation(binding.toolbarUserName, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
            isTitleVisible = false
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS && isTitleContainerVisible) {
            startAlphaAnimation(binding.titleCustom, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
            isTitleContainerVisible = false
        } else if (percentage < PERCENTAGE_TO_HIDE_TITLE_DETAILS && !isTitleContainerVisible) {
            startAlphaAnimation(binding.titleCustom, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
            isTitleContainerVisible = true
        }
    }

    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation =
            if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f)
            else AlphaAnimation(1f, 0f)

        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }
    //endregion
}