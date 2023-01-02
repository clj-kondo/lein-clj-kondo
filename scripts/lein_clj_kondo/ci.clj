(ns lein-clj-kondo.ci
  (:require
   [babashka.tasks :refer [shell]]
   [clojure.string :as str]))

(defn ^:private replace-in-file [file regex content]
  (as-> (slurp file) $
    (str/replace $ regex content)
    (spit file $)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn tag [& [tag]]
  (let [tag (str/replace tag #"^v" "")]
    (shell "git fetch origin")
    (shell "git pull origin HEAD")
    (replace-in-file "project.clj"
                     #"com.github.clj-kondo/lein-clj-kondo \"[0-9]+.[0-9]+.[0-9]+.*\""
                     (format "com.github.clj-kondo/lein-clj-kondo \"%s\"" tag))
    (replace-in-file "CHANGELOG.md"
                     #"## Unreleased"
                     (format "## Unreleased\n\n## v%s" tag))
    (shell "git add project.clj CHANGELOG.md")
    (shell (format "git commit -m \"Release: %s\"" tag))
    (shell (str "git tag v" tag))
    (shell "git push origin HEAD")
    (shell "git push origin --tags")))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn deploy [& _args]
  (shell "lein deploy clojars"))
