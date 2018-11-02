package sbis.faceinfo.presentation.search.view.adapter

import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import sbis.helpers.view.BaseAdapter
import sbis.data.model.PersonSearch
import sbis.faceinfo.R
import sbis.faceinfo.databinding.ItemSearchPersonBinding
import sbis.faceinfo.presentation.search.view.loadPhoto

class SearchPersonAdapter(val listener: OnPersonClickListener) :
    BaseAdapter<SearchPersonAdapter.ViewHolder, PersonSearch>() {

    interface OnPersonClickListener {
        fun onClick(person: PersonSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_search_person, parent, false)
            )!!
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = itemList[position]

        holder.bind(person)
        holder.bindView.userPhoto.loadPhoto(person.photoUrl)
        holder.bindView.root.setOnClickListener { listener.onClick(person) }
    }

    override fun getItemCount() = itemList.size

    class ViewHolder(var bindView: ItemSearchPersonBinding) : RecyclerView.ViewHolder(bindView.root) {

        fun bind(viewModel: PersonSearch) {
            bindView.viewModel = viewModel
            bindView.executePendingBindings()
        }
    }
}