package com.toolstodo.ecommerce.ui.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerce.R
import com.toolstodo.ecommerce.databinding.FragmentDetailBinding
import com.toolstodo.ecommerce.domain.model.product.Product
import com.toolstodo.ecommerce.ui.view.common.recyclerview.images.ImageAdapter
import com.toolstodo.ecommerce.ui.view.common.recyclerview.product.ProductAdapter
import com.toolstodo.ecommerce.ui.view.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), GestureDetector.OnGestureListener {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var product: Product
    private lateinit var imgAdapter: ImageAdapter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var gestureDetector: GestureDetector

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
        imgAdapter = ImageAdapter(emptyList()) {
            loadImg(it)
        }

        productAdapter = ProductAdapter(emptyList()) {
            val navToSelf = DetailFragmentDirections.actionDetailFragmentSelf(it)
            findNavController().navigate(navToSelf)
        }

        gestureDetector = GestureDetector(requireContext(), this)

        initUI()
        initListeners()
        initObservers()

        viewModel.getProductsInCategory(product.category)
        viewModel.verifyFav(product.id)
    }

    private fun initUI() {
        with(binding) {
            if (product.images.isNotEmpty()) {
                loadImg(product.images[0])

                rvImages.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvImages.adapter = imgAdapter
                imgAdapter.updateList(product.images)
            }

            rvSimilarProducts.layoutManager =
                GridLayoutManager(requireContext(),2)
            rvSimilarProducts.adapter = productAdapter

            txtTitle.text = product.title
            txtDiscount.text = getString(R.string.discount_text, product.discountPercentage)
            txtDescription.text = product.description
            txtPrice.text = product.price.toString()
            txtRate.text = product.rating.toString()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnFav.setOnClickListener {

            }

            svFather.setOnTouchListener { _, motionEvent ->
                gestureDetector.onTouchEvent(motionEvent)
            }

            btnFav.setOnClickListener {
                product.isFavorite = !product.isFavorite
                modifyFavIcon()
                viewModel.modifyFav(product)
            }

        }
    }

    private fun initObservers() {
        viewModel.infoState.observe(viewLifecycleOwner) { responseInfo ->
            productAdapter.updateList(responseInfo.products.filter { it.id != product.id })
        }

        viewModel.favState.observe(viewLifecycleOwner) { isFav ->
            product.isFavorite = isFav
            modifyFavIcon()
        }
    }

    private fun loadImg(url: String) {
        with(binding) {
            imgProduct.post {
                Picasso.get().load(url).into(imgProduct)
            }
        }
    }

    private fun modifyFavIcon(){
        val icon = if(product.isFavorite){
            R.drawable.ic_fav
        }else{
            R.drawable.ic_unfav
        }

        binding.btnFav.setImageResource(icon)
    }

    //---- Gesture detector methods
    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {}

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(
        p0: MotionEvent?,
        p1: MotionEvent,
        distanceX: Float,
        distanceY: Float,
    ): Boolean {
        if (distanceY > 40) {
            binding.elImage.collapse()
        } else if (distanceY < 40) {
            binding.elImage.expand()
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {}

    override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }

}
