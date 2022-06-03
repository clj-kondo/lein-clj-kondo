[![Clojars Project](https://img.shields.io/clojars/v/com.github.clj-kondo/lein-clj-kondo.svg)](https://clojars.org/com.github.clj-kondo/lein-clj-kondo)

# lein-clj-kondo

A Leiningen plugin to run [clj-kondo](https://github.com/clj-kondo/clj-kondo).

## Installation

Add the plugin to your `project.clj`:

```clojure
:plugins [[com.github.clj-kondo/lein-clj-kondo "0.1.4"]]
```

## Usage

This plugin accepts one of the following patterns:

* `lein clj-kondo`
  * This lints your `:source-paths` and `:test-paths`, as computed by Leiningen.
  * It is necessary that you have analysed the project beforehand (see below)
* `lein clj-kondo <options>`
  * This is a good place to analyse your project, or to lint directories other than the `:source-paths` and `:test-paths`.
  * For more information on all available options, check the [documentation](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md).

### Lein CLI

``` bash
$ # 1.- Analyse your project:
$ lein clj-kondo --copy-configs --dependencies --lint $classpath
$ # 2.- Lint your source and test paths:
$ lein clj-kondo
```

### Aliases

You can configure your project.clj to add custom aliases to run specific clj-kondo tasks, below you can find a simple example which first lints the project dependencies and then lints the project code:

```clojure
,,,
:aliases {"clj-kondo-deps" ["clj-kondo" "--copy-configs" "--dependencies" "--lint" "$classpath"]
          "clj-kondo-lint" ["do" ["clj-kondo-deps"] ["clj-kondo"]]}
,,,
```

