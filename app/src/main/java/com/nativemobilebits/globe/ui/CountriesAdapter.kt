package com.nativemobilebits.globe.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nativemobilebits.globe.databinding.ItemCountryContentRowBinding
import com.nativemobilebits.globe.databinding.ItemStateContentRowBinding
import com.nativemobilebits.globe.datasource.remote.response.Country
import com.nativemobilebits.globe.datasource.remote.response.States
import com.nativemobilebits.globe.utils.BaseRecyclerViewAdapter
import com.nativemobilebits.globe.utils.BaseViewHolder
import com.nativemobilebits.globe.utils.Utils


class CountriesAdapter(
    private val context: Context,
    private val countries: List<Country>?,
    private val states: List<States>?,
    private val showingCountryData: Boolean,
    private val iCountryItem: ICountryItem?
) : BaseRecyclerViewAdapter() {


    companion object {
        var onContentClick: ICountryItem? = null
    }

    override fun showItems(items: List<Any>?) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return if (showingCountryData) {
            val binding = ItemCountryContentRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            CountryContentViewHolder(binding)
        } else {
            val binding = ItemStateContentRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            StatesViewHolder((binding))
        }

    }

    override fun getItemCount(): Int {
        return if (showingCountryData) countries?.size ?: 0 else states?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        if (showingCountryData) {
            holder as CountryContentViewHolder
            setupCountryRow(holder, countries, position)
        } else {
            holder as StatesViewHolder
            setUpStateRow(holder, states, position)
        }
    }

    private fun setUpStateRow(
        holder: StatesViewHolder,
        states: List<States>?,
        position: Int
    ) {
        states?.also {
            holder.view.tvStateTitle.text = it[position].name
            holder.view.tvStateCode.text = it[position].state_code
        }
    }

    private fun setupCountryRow(
        holder: CountryContentViewHolder,
        countries: List<Country>?,
        position: Int
    ) {
        onContentClick = iCountryItem
        countries?.also {
            holder.view.tvCountryContentTitle.text = it[position].name ?: ""
            it[position].flag?.also { url ->
                Log.d("Adapter", "URL = $position = $url")
                Utils.fetchSvg(context, url, holder.view.ivCountryIcon)
            }

            holder.view.cardView.setOnClickListener {
                iCountryItem?.onContentClick(countries[position])
            }
        }
    }


    inner class CountryContentViewHolder(val view: ItemCountryContentRowBinding) :
        BaseViewHolder(view.root)

    inner class StatesViewHolder(val view: ItemStateContentRowBinding) :
        BaseViewHolder(view.root)


    open interface ICountryItem {
        fun onContentClick(country: Country)
    }

}