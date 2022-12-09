https://jitpack.io/#netigenkluzowicz/api_android

## Samples of usage:
https://github.com/netigenkluzowicz/api_android/tree/develop/sample-app

## WebView Survey

It's implemented in WebView, docs: 

https://github.com/netigenkluzowicz/apis_strapi/blob/develop/documentation/webview-survey.md


In Main Activity override fun openSurveyFragment():

```Kotlin
class MainActivity : CoreMainActivity() {

    override fun openSurveyFragment() = findNavController(R.id.layoutHomeContainer)
        .safeNavigate(R.id.action_homeFragment_to_surveyFragment)

}
```
Example of Survey Fragment:

```Kotlin
class SurveyFragment : NetigenVMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_survey, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        Survey.showSurvey(webView, BuildConfig.VERSION_NAME) { surveyEvent: SurveyEvent ->
            Timber.d("xxx.+surveyAction = [$surveyEvent]")
            // Log event to firebase
            val defaultEvent = surveyEvent.defaultFirebaseEvent()
            sendFirebaseEvent(defaultEvent.name, defaultEvent.bundle)
            if (surveyEvent is SurveyEvent.ExitEvent) {
                // survey exits so navigate back
                requireActivity().onBackPressed()
            }
        }
    }

    // Log event to firebase
    private fun sendFirebaseEvent(name: String, bundle: Bundle) {
        firebaseAnalytics.logEvent(name, bundle)
    }
}
```


## Add language fragment to application:

You need to add this code to your Application:

```Kotlin
override fun onCreate() {
    super.onCreate()
    ChangeLanguageHelper.setSharedPreferences(this)
}
```

This code to your Activity:

```Kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ChangeLanguageHelper.setActivityLocale(this)
}
```

This code to your Activity:

```Kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ChangeLanguageHelper.setActivityLocale(this)
}
```

This code to your Fragments or BaseFragment:

```Kotlin
override fun onAttach(context: Context) {
     super.onAttach(context)
     ChangeLanguageHelper.setActivityLocale(requireActivity())
}

```
If you need current language call up:

```Kotlin
    ChangeLanguageHelper.getPreferencesLocale()
```


```Kotlin
class LanguageFragment : BaseLanguageFragment<FragmentLanguageBinding>() {

    private fun initClickListener() {
        binding.run {
            languageBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguageFragment()
    }

    override fun initView() {
        initClickListener()
    }

    override val recyclerView: RecyclerView
        get() = binding.languageRecyclerView
    
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentLanguageBinding.inflate(inflater, container, false)

    override fun getLanguage() = listOf(
        "af", "ar", "az", "bg", "bn", "cs", "da", "de", "en", "es", "et", "fa", "fi", "fr", "he", "hi", "hr", "hu", "id", "it", "ja", "ka",
        "kk", "ko", "lt", "lv", "mk", "mn", "ms", "ne", "nl", "no", "pl", "pt", "ro", "ru", "sk", "sl", "sr", "sv", "sw", "th", "tr", "uk",
        "vi", "zh", "zu"
    )
}
```


