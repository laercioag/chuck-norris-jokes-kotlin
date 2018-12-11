package com.laercioag.chuckynorrisjokes.presentation.randomjoke

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.extensions.invisible
import com.laercioag.chuckynorrisjokes.extensions.loadGif
import com.laercioag.chuckynorrisjokes.extensions.loadImage
import com.laercioag.chuckynorrisjokes.extensions.visible
import com.laercioag.chuckynorrisjokes.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_random_joke.*
import kotlinx.android.synthetic.main.layout_footer.*
import javax.inject.Inject


class RandomJokeFragment : BaseFragment(), RandomJokeContract.View {

    @Inject
    lateinit var presenter: RandomJokeContract.Presenter

    companion object {
        private const val ARGUMENT_CATEGORY = "category"

        fun newInstance(category: Category): RandomJokeFragment {
            val bundle = Bundle()
            bundle.putSerializable(ARGUMENT_CATEGORY, category)
            val fragment = RandomJokeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_random_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category: Category? = arguments?.getSerializable(ARGUMENT_CATEGORY) as Category
        presenter.attach(this)
        category?.let {
            presenter.getRandomJoke(it)
        }
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.invisible()
    }

    override fun handleError(throwable: Throwable) {
        errorLayout.visible()
        Log.e(RandomJokeFragment::class.java.simpleName, "Error", throwable)
    }

    override fun showJoke(joke: Joke) {
        icon.loadImage(joke.iconUrl)
        icon.visible()
        footerImage.loadGif(R.raw.chuck_norris_dancing)
        footer.visible()
        phrase.text = joke.value
        phrase.visible()
        link.setOnClickListener { openLink(joke.url) }
        link.visible()
    }

    private fun openLink(uriString: String) {
        CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(requireContext(), R.color.primaryColor))
            .build()
            .launchUrl(requireContext(), Uri.parse(uriString))
    }

}

