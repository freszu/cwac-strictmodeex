CWAC StrictModeEx: Punishment for the Bad Code In Your App
==========================================================

`StrictMode` is a handy feature in API Level 9 and higher,
telling you where your Android application is doing things
it probably should not on the main application thread.

In the spirit of `StrictMode`, the `StrictModeEx` project
offers classes to help you diagnose similar sorts of problems
beyond what `StrictMode` itself offers.

Right now, that consists of one class: `StrictAdapter`. This
`ListAdapter` wrapper will log slow-running `getView()` calls,
plus optionally give you an overall performance view on how
your `Adapter` is doing in the code you control.

Installation
------------
This Android library project is 
[available as a JAR](https://gihub.com/commonsguy/cwac-strictmodeex/releases).
If you wish to use the JAR, you will need to also add the JAR from
[the CWAC-Adapter project](http://github.com/commonsguy/cwac-adapter) to your
project.

NOTE: The JAR name, as of v0.3.1, has a `cwac-` prefix, to help distinguish it from other JARs.

Also note that if you plan to use this as an Android library project
in source form, you
will also need to download [the CWAC-Adapter project](http://github.com/commonsguy/cwac-adapter)
(and, if needed, modify this project's configuration to point to your copy of
CWAC-Adapter's library project). Alternatively, download the CWAC-Adapter JAR into
the `libs/` directory of your clone of this project and remove the dependency on
the CWAC-Adapter library project.

This project is also available as
an artifact for use with Gradle. To use that, add the following
blocks to your `build.gradle` file:

```groovy
repositories {
    maven {
        url "https://repo.commonsware.com.s3.amazonaws.com"
    }
}

dependencies {
    compile 'com.commonsware.cwac:strictmodeex:0.3.+'
}
```

Or, if you cannot use SSL, use `http://repo.commonsware.com` for the repository
URL. This should automatically pull down the CWAC-Adapter dependency.

Usage: StrictAdapter
--------------------
Normally, you create your own `ListAdapter` (e.g., an `ArrayAdapter`)
and give that to your `AdapterView` (e.g., `setListAdapter()` on a
`ListFragment`).

Now, you create your own `ListAdapter`, wrap it in a `StrictAdapter`,
and put the `StrictAdapter` in the `AdapterView`. To do this, just
use the `StrictAdapter` constructor, which takes your regular
`ListAdapter` as a parameter.

By default, the `StrictAdapter` will log all calls to `getView()`
that take longer than 1ms (1000000ns). You can alter this by
calling `setThreshold()`, which takes the desired threshold
time in nanoseconds.

Any `getView()` call that takes longer than the threshold is logged
with error severity to LogCat. By default, the tag will be `StrictAdapter`,
though you can change this via a call to `setLogTag()`.

If you want, you can dump the overall performance metrics to LogCat
(debug severity) via a call to `dumpResultsToLog()`. This will report
the total number of `getView()` calls, the number that were penalized
(i.e., exceeded the threshold), the total time across all the calls,
and the mean time per call. A call to `reset()` clears the statistics.

Even on production hardware with a trivial `ListAdapter` (such as the
one in the `demo/` sub-project), you *will* find `getView()` calls that
exceed the default 1ms threshold. It should only be a few percent of
the total number of calls, and it could easily represent background
work from other processes that happened to steal a time slice while
your `getView()` was running.

Version
-------
This is version v0.3.1 of this module, meaning it is brand-spankin'
new.

Demo
----
In the `demo/` sub-project you will find
a sample activity that demonstrates the use of `StrictAdapter`.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
If you have questions regarding the use of this code, please post a question
on [StackOverflow](http://stackoverflow.com/questions/ask) tagged with
`commonsware-cwac` and `android` after [searching to see if there already is an answer](https://stackoverflow.com/search?q=[commonsware-cwac]+strictmodeex). Be sure to indicate
what CWAC module you are having issues with, and be sure to include source code 
and stack traces if you are encountering crashes.

If you have encountered what is clearly a bug, or a feature request,
please post an [issue](https://github.com/commonsguy/cwac-strictmodeex/issues).
Be certain to include complete steps for reproducing the issue.

Do not ask for help via Twitter.

Also, if you plan on hacking
on the code with an eye for contributing something back,
please open an issue that we can use for discussing
implementation details. Just lobbing a pull request over
the fence may work, but it may not.

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

Release Notes
-------------
- v0.3.1: Gradle updates, fixed manifest for merging, added `cwac-` to JAR
- v0.3.0: migrated to Gradle, published AAR
- v0.1.0: initial release
