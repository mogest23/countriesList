package com.moges.countrieslist.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moges.countrieslist.R
import com.moges.countrieslist.data.remote.RetrofitInstance
import com.moges.countrieslist.data.repo.CountriesRepo
import com.moges.countrieslist.databinding.FragmentCountriesBinding
import com.moges.countrieslist.presentation.viewmodels.CountriesState
import com.moges.countrieslist.presentation.viewmodels.CountriesViewModel
import com.moges.countrieslist.presentation.viewmodels.FactoryCountriesViewModel
import kotlin.getValue

class CountriesFragment : Fragment(R.layout.fragment_countries) {
    // Use ViewBinding to better manage our UI logic
    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    // Initialize the Repository to manually inject into the ViewModel.
    // If we use Hilt, this would be handled automatically
    private val countriesRepo = CountriesRepo(RetrofitInstance.countriesService)

    // Initialize viewModel
    private val viewModel: CountriesViewModel by viewModels {
        FactoryCountriesViewModel(countriesRepo)
    }

    // Our adapter to display the countries
    val adapter = CountriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycledView = binding.recyclerView
        recycledView.adapter = adapter
        recycledView.layoutManager = LinearLayoutManager(requireContext())

        // This button will be available for refreshing our page if loading fails for some reason
        binding.retryBtn.setOnClickListener {
            viewModel.getCountries()
        }

        // Update countries fragment based on the state of the ViewModel
        viewModel.countriesState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CountriesState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorLayout.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }

                is CountriesState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.errorLayout.visibility = View.GONE
                    adapter.submitList(state.countries)
                }

                is CountriesState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorText.text = state.message
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}