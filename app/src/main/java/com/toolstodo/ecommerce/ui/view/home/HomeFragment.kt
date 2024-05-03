package com.toolstodo.ecommerce.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.FragmentHomeBinding
import com.toolstodo.ecommerce.ui.view.common.recyclerview.category.CategoryAdapter
import com.toolstodo.ecommerce.ui.view.common.recyclerview.product.ProductAdapter
import com.toolstodo.ecommerce.ui.view.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        initUI()
        initListeners()
        initObservers()
    }

    private fun initUI() {
        productAdapter = ProductAdapter(emptyList()) {
            val navToDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(product = it)
            findNavController().navigate(navToDetail)
        }

        categoryAdapter = CategoryAdapter(emptyList())

        with(binding) {
            rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProducts.adapter = productAdapter

            rvCategories.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvCategories.adapter = categoryAdapter
        }
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        viewModel.infoState.observe(viewLifecycleOwner) { responseInfo ->
            productAdapter.updateList(responseInfo.products)
        }

        viewModel.categoryState.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.updateList(categories)
        }

    }

}