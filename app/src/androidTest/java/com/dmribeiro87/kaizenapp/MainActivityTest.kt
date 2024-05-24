import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dmribeiro87.kaizenapp.R
import com.dmribeiro87.kaizenapp.RecyclerViewMatcher
import com.dmribeiro87.kaizenapp.gamesFeature.presentation.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        Thread.sleep(5000)
    }

    @After
    fun cleanup() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testElementsVisibility() {
        onView(withId(R.id.rvSports)).check(matches(isDisplayed()))
        onView(withRecyclerView(R.id.rvSports).atPositionOnView(0, R.id.switchFavorites)).check(matches(isDisplayed()))
        onView(withRecyclerView(R.id.rvSports).atPositionOnView(0, R.id.tvSportName)).check(matches(isDisplayed()))
    }

    @Test
    fun testSwitchFavorites() {
        onView(withRecyclerView(R.id.rvSports).atPositionOnView(0, R.id.switchFavorites)).perform(click())
        onView(withRecyclerView(R.id.rvSports).atPositionOnView(0, R.id.switchFavorites)).check(matches(
            isChecked()
        ))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}