https://jitpack.io/#netigenkluzowicz/api_android

2.x branches are AndroidX migration branches 1.x branches are old Android support branches

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


