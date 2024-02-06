package pl.netigen.coreapi.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Base class for [ICoreMainVM] implementation, extends [AndroidViewModel] because it must be available from any Fragment or Activity
 *
 * For easy access in fragments use [NetigenVMFragment][pl.netigen.core.fragment.NetigenVMFragment]
 *
 * see: [ICoreMainVM], [ICoreMainActivity]
 *
 * @param application [Application]  context of this class
 */
abstract class CoreMainVM(application: Application) : AndroidViewModel(application), ICoreMainVM
