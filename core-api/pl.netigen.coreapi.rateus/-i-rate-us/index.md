---
title: IRateUs - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.rateus](../index.md) / [IRateUs](./index.md)

# IRateUs

`interface IRateUs`

Rate us module is used for show user dialog to encourage him to rate application in store and/or write application review

### Properties

| [numberOfChecksBeforeShowingDialog](number-of-checks-before-showing-dialog.html) | After this number of calls [increaseOpeningCounter](increase-opening-counter.html), rate us dialog will be showed`abstract val numberOfChecksBeforeShowingDialog: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |
| [openingCounter](opening-counter.html) | Counts calls of [increaseOpeningCounter](increase-opening-counter.html)`abstract val openingCounter: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |

### Functions

| [clickLater](click-later.html) | Called on click in dialog, after that [openingCounter](opening-counter.html) is reset, and dialog will be showed later again`abstract fun clickLater(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [clickNo](click-no.html) | Called on click in dialog, after that rate us dialog will not be showed again`abstract fun clickNo(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [clickYes](click-yes.html) | Called on click in dialog`abstract fun clickYes(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [doNotShowRateUsAgain](do-not-show-rate-us-again.html) | Saves info that user don't wants to see Rate Us dialog again`abstract fun doNotShowRateUsAgain(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [increaseOpeningCounter](increase-opening-counter.html) | It should be called to count number of times user launches app`abstract fun increaseOpeningCounter(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [openRateDialog](open-rate-dialog.html) | Shows Rate Us dialog`abstract fun openRateDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [openRateDialogIfNeeded](open-rate-dialog-if-needed.html) | Checks if [shouldOpenRateUs](should-open-rate-us.html) and if it is true shows Rate Us dialog`abstract fun openRateDialogIfNeeded(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [shouldOpenRateUs](should-open-rate-us.html) | Checks how many user uses app [openingCounter](opening-counter.html), and shows Rate Us dialog when this reach [numberOfChecksBeforeShowingDialog](number-of-checks-before-showing-dialog.html)`abstract fun shouldOpenRateUs(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

