---
title: RateUs - api_android
---

[api_android](../../index.html) / [pl.netigen.core.rateus](../index.html) / [RateUs](./index.html)

# RateUs

`class RateUs : `[`IRateUs`](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html)

[IRateUs](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html) implementation

### Types

| [Builder](-builder/index.html) | `class Builder` |

### Properties

| [numberOfChecksBeforeShowingDialog](number-of-checks-before-showing-dialog.html) | After this number of calls [increaseOpeningCounter](../../pl.netigen.coreapi.rateus/-i-rate-us/increase-opening-counter.html), rate us dialog will be showed`val numberOfChecksBeforeShowingDialog: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [openingCounter](opening-counter.html) | Counts calls of [increaseOpeningCounter](../../pl.netigen.coreapi.rateus/-i-rate-us/increase-opening-counter.html)`val openingCounter: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| [clickLater](click-later.html) | Called on click in dialog, after that [openingCounter](../../pl.netigen.coreapi.rateus/-i-rate-us/opening-counter.html) is reset, and dialog will be showed later again`fun clickLater(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clickNo](click-no.html) | Called on click in dialog, after that rate us dialog will not be showed again`fun clickNo(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clickYes](click-yes.html) | Called on click in dialog`fun clickYes(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [doNotShowRateUsAgain](do-not-show-rate-us-again.html) | Saves info that user don't wants to see Rate Us dialog again`fun doNotShowRateUsAgain(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [increaseOpeningCounter](increase-opening-counter.html) | It should be called to count number of times user launches app`fun increaseOpeningCounter(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openRateDialog](open-rate-dialog.html) | Shows Rate Us dialog`fun openRateDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openRateDialogIfNeeded](open-rate-dialog-if-needed.html) | Checks if [shouldOpenRateUs](../../pl.netigen.coreapi.rateus/-i-rate-us/should-open-rate-us.html) and if it is true shows Rate Us dialog`fun openRateDialogIfNeeded(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [shouldOpenRateUs](should-open-rate-us.html) | Checks how many user uses app [openingCounter](../../pl.netigen.coreapi.rateus/-i-rate-us/opening-counter.html), and shows Rate Us dialog when this reach [numberOfChecksBeforeShowingDialog](../../pl.netigen.coreapi.rateus/-i-rate-us/number-of-checks-before-showing-dialog.html)`fun shouldOpenRateUs(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

