[![Clojars Project](https://img.shields.io/clojars/v/com.github.clj-kondo/lein-clj-kondo.svg)](https://clojars.org/com.github.clj-kondo/lein-clj-kondo)

# lein-clj-kondo

A Leiningen plugin to run [clj-kondo](https://github.com/clj-kondo/clj-kondo).

## Installation

Add the plugin to your `project.clj`:

```clojure
:plugins [[com.github.clj-kondo/lein-clj-kondo "0.1.3"]]
```

## Usage

This plugin accepts the following pattern `clj-kondo <options>`.

For more information on all available options, Check the [documentation](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md).

### lein CLI

``` bash
$ lein clj-kondo --lint $classpath
```

### Aliases

You can configure your project.clj to add custom aliases to run specific clj-kondo tasks, below you can find a simple example which first lint the project dependencies and then lint the project code:

```clojure
,,,
:aliases {"clj-kondo-deps" ["clj-kondo" "--copy-configs" "--dependencies" "--lint" "$classpath"]
          "clj-kondo" ["do" ["clj-kondo-deps"] ["clj-kondo" "--lint" "src" "test"]]}
,,,
```

