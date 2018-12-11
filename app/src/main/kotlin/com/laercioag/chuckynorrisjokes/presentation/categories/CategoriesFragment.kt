package com.laercioag.chuckynorrisjokes.presentation.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.extensions.invisible
import com.laercioag.chuckynorrisjokes.extensions.visible
import com.laercioag.chuckynorrisjokes.presentation.base.BaseFragment
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokeActivity
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

class CategoriesFragment : BaseFragment(), CategoriesContract.View {

    @Inject
    lateinit var presenter: CategoriesContract.Presenter

    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSwipeRefreshLayout()
        presenter.attach(this)
        presenter.getCategories()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun showCategories(categories: List<Category>) {
        errorLayout.invisible()
        recyclerView.visible()
        adapter.setItems(categories)
    }

    override fun showLoading() {
        if (!swipeRefreshLayout.isRefreshing) {
            loading.visible()
        }
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
        loading.invisible()
    }

    override fun handleError(throwable: Throwable) {
        recyclerView.invisible()
        errorLayout.visible()
        Log.e(CategoriesFragment::class.java.simpleName, "Error", throwable)
    }

    private fun setupRecyclerView() {
        adapter = CategoriesAdapter()
        adapter.itemListener = this::navigateToRandomJoke
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.getCategories()
        }
    }

    private fun navigateToRandomJoke(category: Category) {
        val intent = Intent(requireContext(), RandomJokeActivity::class.java)
        val args = RandomJokeActivity.args(category)
        intent.putExtras(args)
        requireActivity().startActivityFromFragment(this, intent, 0)
    }

}
