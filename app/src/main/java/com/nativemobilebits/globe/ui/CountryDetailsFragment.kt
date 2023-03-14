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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.nativemobilebits.globe.R
import com.nativemobilebits.globe.databinding.FragmentCountryDetailsBinding
import com.nativemobilebits.globe.datasource.ResourceState
import com.nativemobilebits.globe.datasource.remote.entity.RequestQuery
import com.nativemobilebits.globe.datasource.remote.response.Country
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import com.nativemobilebits.globe.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailsBinding
    private val args: CountryDetailsFragmentArgs by navArgs()
    lateinit var viewModel: CountryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_details, container, false)
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"Inside_CountryDetailsFragment")
        Log.d(TAG,"== ${Gson().toJson(args.country)}")
        setUpObservers()
        setUpUI(args.country)
        getCountryDetails(args.country)
    }

    private fun getCountryDetails(country: Country) {
        val requestQuery = RequestQuery(country?.name?:"")
        viewModel.getCountryDetails(requestQuery)
    }

    private fun setUpUI(country: Country) {
        country?.name?.also {
            binding.toolbar.titleAppCompatTextView.text = it
        }
        country?.flag?.also {
            Utils.fetchSvg(context,it,binding.toolbar.ivCountryLogo)
        }
        binding.toolbar.backImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setUpObservers() {
        viewModel.countryDetailsResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResourceState.Success -> {
                    Log.d(TAG,"Inside_Success")
                    setUpStatesList(it.data)
                    binding.rrProgressBar.visibility = View.GONE
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



    private fun setUpStatesList(data: CountryDetailsResponse) {
        binding.rvStates.apply {
            visibility = View.VISIBLE
            adapter = CountriesAdapter(requireContext(),null,data.data?.states,false,null)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


    companion object{
        const val TAG = "CountryDetailsFragment"
    }
}