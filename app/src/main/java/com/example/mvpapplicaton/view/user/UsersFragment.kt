package com.example.mvpapplicaton.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpapplicaton.App
import com.example.mvpapplicaton.databinding.FragmentUsersBinding
import com.example.mvpapplicaton.model.cache.room.RoomGithubRepositoriesCache
import com.example.mvpapplicaton.model.reposetory.image.GlideImageLoader
import com.example.mvpapplicaton.model.data.ApiHolder
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.reposetory.githubuser.RetrofitGithubRepositoriesRepo
import com.example.mvpapplicaton.model.reposetory.githubuser.RetrofitGithubUsersRepo
import com.example.mvpapplicaton.network.AndroidNetworkStatus
import com.example.mvpapplicaton.presenter.navigation.AndroidScreens
import com.example.mvpapplicaton.presenter.user.UsersPresenter
import com.example.mvpapplicaton.view.BackButtonListener
import com.example.mvpapplicaton.view.main.adapter.UsersRVAdapter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null
    var adapter: UsersRVAdapter? = null

    @Inject lateinit var database: Database
    @Inject lateinit var router: Router

    private val presenter: UsersPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UsersPresenter(
            AndroidSchedulers.mainThread(),
//            RetrofitGithubRepositoriesRepo(ApiHolder.api,
//                AndroidNetworkStatus(App.instance), RoomGithubRepositoriesCache(database)),
//            router,
//            user,
//            AndroidScreens()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UsersFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
            App.instance.appComponent.inject(this)
        }
    }
}