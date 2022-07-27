package com.dazn.playerapp.di

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.events.domain.GetDataUseCase
import com.dazn.playerapp.events.ui.EventsPresenter
import com.dazn.playerapp.schedule.ui.SchedulePresenter
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: PlayerService)

    fun inject(useCase: GetDataUseCase)

    fun inject(presenter: EventsPresenter)

    fun inject(presenter: SchedulePresenter)
}
/**
 * Consider usage of Android Dagger capabilities:
 *
 * 1. Custom Application class that will extend dagger.android.support.DaggerAppCompatActivity
 *
 * class MyCustomApp : DaggerApplication() {
 *
 *     override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
 *         DaggerMyAppComponent.factory().create(this)
 * }
 *
 *
 * 2. Each Fragment should extend dagger.android.support.DaggerFragment
 *
 * class MyFragment : DaggerFragment()
 *
 *
 * 3. Each Activity should extend dagger.android.support.DaggerAppCompatActivity
 *
 * class MyActivity : DaggerAppCompatActivity()
 *
 *
 * 4. Top level component
 *
 * @Singleton
 * @Component(
 *     modules = [
 *         AndroidInjectionModule::class,
 *         ApiModule::class
 *     ]
 * )
 * interface MyAppComponent : AndroidInjector<MyCustomApp> {
 *
 *     @Component.Factory
 *     interface Factory : AndroidInjector.Factory<MyCustomApp>
 * }
 *
 *
 * 5. Activities and Fragments should be binded using @ContributesAndroidInjector
 *
 * @Module
 * abstract class MyActivityModule {
 *
 *     @ContributesAndroidInjector
 *     abstract fun contributesMyActivity(): MyActivity
 * }
 *
 * @Module
 * abstract class MyFragmentModule {
 *
 *     @ContributesAndroidInjector
 *     abstract fun contributesMyFragment(): MyFragment
 * }
 *
 *
 * 6. Use constructor injection wherever possible
 *
 * class PlayerService @Inject constructor(private val api: PlayerApi)
 *
 *
 * 7. Use fields injection only in classes that are outside our control (managed via framework like Activities, Fragments, Application, Services, Broadcast Receiver)
 *
 * class EventsFragment : DaggerFragment(), EventListAdapter.OnItemClickListener, EventsContract.View {
 *
 *     @Inject
 *     lateinit var presenter: EventsContract.Presenter
 * }
 *
 *
 * 8. Try to bind interface with it's implementation
 *
 * @Module
 * abstract class EventsModule {
 *
 *     @Binds
 *     abstract fun bindsEventsPresenter(eventsPresenter: EventsPresenter): EventsContract.Presenter
 * }
 */