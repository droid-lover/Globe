package com.nativemobilebits.globe.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nativemobilebits.globe.R
import com.nativemobilebits.globe.navigation.CountryModuleRoute
import com.nativemobilebits.globe.databinding.FragmentHomeBinding
import com.nativemobilebits.globe.datasource.ResourceState
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.Country
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var countriesAdapter: CountriesAdapter

    lateinit var viewModel: CountryViewModel


    @Inject
    lateinit var countryModuleRoute: CountryModuleRoute

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getCountries()
    }

    private fun setUpObservers() {
        viewModel.countriesDataResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResourceState.Success -> {
                    Log.d(TAG,"Inside_Success")
                    binding.rrProgressBar.visibility = View.GONE
                    setUpCountriesList(it.data)
                }
                is ResourceState.Error -> {
                    Log.d(TAG,"Inside_Error")
                    binding.rrProgressBar.visibility = View.GONE
                }
                is ResourceState.Loading -> {
                    Log.d(TAG,"Inside_Loading")
                    binding.rrProgressBar.visibility = View.VISIBLE
                }
            }
        })

        viewModel.noInternet.observe(viewLifecycleOwner,Observer{
            if(it) binding.tvNoInternet.visibility = View.VISIBLE else View.GONE
        })
    }

    private fun setUpCountriesList(data: CountriesResponse) {
        binding.rvCountries.apply {
            visibility = View.VISIBLE
            adapter = CountriesAdapter(requireContext(),data.data, null,true,object : CountriesAdapter.ICountryItem {
                override fun onContentClick(country: Country) {
                    goToDetailsScreen(country)
                }
            })
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun goToDetailsScreen( country: Country) {
        countryModuleRoute.showCountryDetails(findNavController(), country )
    }

    companion object{
        private val TAG = HomeFragment::class.java.simpleName
    }
}