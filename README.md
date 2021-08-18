# lein-clj-kondo

A Leiningen plugin to run [clj-kondo](https://github.com/clj-kondo/clj-kondo).

## Installation

Add the plugin to your `project.clj`:

```clojure
:plugins [[clj-kondo/lein-clj-kondo "0.1.0"]]
```

## Usage

This plugin accepts the following pattern `clj-kondo <options>`.

For more information on all available options, Check the [documentation](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md).

### lein CLI

``` bash
$ lein clj-kondo --lint $(lein classpath)
```

### Aliases

TODO
