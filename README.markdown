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

This is [available as a JAR file](https://github.com/commonsguy/downloads).
The project itself is set up as an Android
library project, in case you wish to use the source code in
that fashion.

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

Dependencies
------------
This project relies upon the [CWAC AdapterWrapper][adapter] project.
A copy of compatible JARs can be found in the `libs/` directory
of the project, though you are welcome to try newer ones, or
ones that you have patched yourself.

This project should work on API Level 7 and higher, except for any portions that
may be noted otherwise in this document. Please report bugs if you find features
that do not work on API Level 7 and are not noted as requiring a higher version.

Version
-------
This is version v0.1 of this module, meaning it is brand-spankin'
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
on [StackOverflow](http://stackoverflow.com/questions/ask) tagged with `commonsware` and `android`. Be sure to indicate
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
* v0.1.0: initial release
