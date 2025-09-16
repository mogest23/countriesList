package com.moges.countrieslist.presentation.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.moges.countrieslist.R
import com.moges.countrieslist.databinding.CountryTileBinding
import com.moges.countrieslist.models.Country

// We use Diff to avoid unnecessary updates to the list
class CountriesAdapter
    : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(CountryDiff) {

    inner class CountryViewHolder(val binding: CountryTileBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryTileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)

        holder.binding.countryAndRegionTv.text = buildString {
            append(country.name)
            if (country.region.isNotEmpty()) append(", ")
            append(country.region)
        }
        holder.binding.countryCodeTv.text = country.code
        holder.binding.capitalCityTv.text = country.capital.ifEmpty { "N/A" }
    }

    private object CountryDiff : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}