package com.toolstodo.ecommerce.ui.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.FragmentSearchBinding
import com.toolstodo.ecommerce.ui.view.common.recyclerview.productsearch.ProductSearchAdapter
import com.toolstodo.ecommerce.ui.view.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()

    private lateinit var productSearchAdapter: ProductSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }

    private fun initComponents() {
        viewModel.query = args.query
        productSearchAdapter = ProductSearchAdapter(emptyList()) {
            val navToDetail = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            findNavController().navigate(navToDetail)
        }

        initUI()
        initListeners()
        initObservers()

        viewModel.getProductsByName(viewModel.query, limit = viewModel.limit, skip = viewModel.skip)
    }

    private fun initUI() {
        with(binding) {
            svSearcher.setQuery(viewModel.query, false)

            rvProductSearch.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvProductSearch.adapter = productSearchAdapter
        }
    }

    private fun initListeners() {
        with(binding) {
            svSearcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.query = query!!
                    viewModel.getProductsByName(
                        viewModel.query,
                        limit = viewModel.limit,
                        skip = viewModel.skip
                    )
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
        }
    }

    private fun initObservers() {
        viewModel.infoState.observe(viewLifecycleOwner) { responseInfo ->
            productSearchAdapter.updateList(responseInfo.products)
            binding.rvProductSearch.scrollToPosition(0)
        }
    }

}