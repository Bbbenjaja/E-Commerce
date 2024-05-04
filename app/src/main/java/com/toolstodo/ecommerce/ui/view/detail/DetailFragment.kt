package com.toolstodo.ecommerce.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.toolstodo.ecommerce.databinding.FragmentDetailBinding
import com.toolstodo.ecommerce.domain.model.product.Product

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        product = args.product

        initUI()
        initListeners()
        initObservers()
    }

    private fun initUI() {
        with(binding){
            txtTitle.text = product.title
        }
    }
    private fun initObservers() {

    }
    private fun initListeners() {

    }

}
