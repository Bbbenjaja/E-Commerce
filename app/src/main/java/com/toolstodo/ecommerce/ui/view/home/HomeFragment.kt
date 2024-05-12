package com.toolstodo.ecommerce.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.FragmentHomeBinding
import com.toolstodo.ecommerce.domain.model.category.Category
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
    private lateinit var allCategory: Category

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        allCategory = Category(getString(R.string.all), true)

        initUI()
        initListeners()
        initObservers()
    }

    private fun initUI() {
        productAdapter = ProductAdapter(emptyList()) {
            val navToDetail =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(product = it)
            findNavController().navigate(navToDetail)
        }

        categoryAdapter = CategoryAdapter(mutableListOf()) { category ->
            viewModel.fetchProductsInCategory(category, viewModel.limit, viewModel.skip)
            viewModel.refreshCategorySelected(viewModel.categoryState.value?.map {
                it.isSelected = it.name == category
                it
            }!!)
            allCategory.isSelected = false
            validateAllCategory()
        }

        with(binding) {
            rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProducts.adapter = productAdapter

            rvCategories.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvCategories.adapter = categoryAdapter
        }
    }

    private fun initListeners() {
        with(binding) {
            cvCategory.setOnClickListener {
                allCategory.isSelected = true
                validateAllCategory()

                if (allCategory.isSelected) {
                    viewModel.fetchResponseInfo(limit = viewModel.limit, skip = viewModel.skip)
                    viewModel.fetchCategories()
                }

                rvProducts.scrollToPosition(0)
            }

            svSearcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    val navToSearch =
                        HomeFragmentDirections.actionHomeFragmentToSearchFragment(query!!)
                    findNavController().navigate(navToSearch)
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
            productAdapter.updateList(responseInfo.products)
        }

        viewModel.categoryState.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.updateList(categories)
            binding.rvCategories.scrollToPosition(viewModel.categoryState.value!!.indexOfFirst { it.isSelected })
        }

    }

    private fun validateAllCategory() {
        with(binding) {
            if (allCategory.isSelected) {
                cvCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.accent
                    )
                )

                txtCategory.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.text_color_accent
                    )
                )
            } else {
                cvCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primary
                    )
                )

                txtCategory.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.text_color_primary
                    )
                )
            }
        }
    }

}