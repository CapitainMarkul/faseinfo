package sbis.faceinfo.presentation.search.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import sbis.App
import sbis.data.model.PersonSearch
import sbis.faceinfo.R
import sbis.faceinfo.databinding.ActivitySearchBinding
import sbis.faceinfo.presentation.search.contracts.SearchPresenterContract
import sbis.faceinfo.presentation.search.contracts.SearchViewModelContract
import sbis.faceinfo.presentation.search.view.adapter.SearchPersonAdapter
import sbis.faceinfo.presentation.search.interactor.SearchInteractor
import sbis.faceinfo.presentation.search.presenter.SearchPresenter
import sbis.faceinfo.presentation.search.viewModel.SearchViewModel
import sbis.helpers.arch.base.BaseActivity
import sbis.helpers.view.ItemListDecorator
import java.util.concurrent.TimeUnit

class SearchActivity : BaseActivity<SearchPresenterContract, SearchViewModelContract>() {

//    companion object {
//        fun createIntent(context: Context, hall: Hall, table: Table, guestList: List<GuestNewModel>?, warehouseId: UUID, showWaiterPhoto: Boolean): Intent =
//            Intent(context, OrderActivity::class.java).apply {
//                putExtra(ARG_HALL, hall)
//                putExtra(ARG_TABLE, table)
//                guestList?.let { putParcelableArrayListExtra(ARG_GUEST_LIST, ArrayList(it)) }
//                putExtra(ARG_WAREHOUSE_ID, warehouseId)
//                putExtra(ARG_SHOW_WAITER_PHOTO, showWaiterPhoto)
//            }
//    }

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchPersonAdapter: SearchPersonAdapter

    override fun createPresenter(): SearchPresenterContract =
        SearchPresenter(SearchInteractor(App.get().getNetworkService()))

    override fun createViewModel(): SearchViewModelContract =
        ViewModelProviders.of(this).get(SearchViewModel::class.java)

    private val searchPersonListener = object : SearchPersonAdapter.OnPersonClickListener {
        override fun onClick(person: PersonSearch) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel

        searchPersonAdapter = SearchPersonAdapter(searchPersonListener).apply {
            setItems(viewModel.searchPersons.value ?: emptyList())
        }

        binding.rvSearchResult.apply {
            adapter = searchPersonAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(
                ItemListDecorator(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.list_divider
                    )!!, true
                )
            )
        }

//        compositeSubscription.add(
        val searchStartCount = 4
        binding.etxtSearchRequest.let { it ->
            RxTextView.afterTextChangeEvents(it)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { _ -> it.text.toString().length > searchStartCount }
                .map<String> { _ -> it.text.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { searchRequest -> presenter.updateSearchRequest(searchRequest) }
        }
//        )
//        RxTextView.
    }

    override fun createSubscribers() {
        viewModel.searchPersons.observe(this@SearchActivity, Observer { items ->
            items?.let { searchPersonAdapter.setItems(it) }
        })
    }
}