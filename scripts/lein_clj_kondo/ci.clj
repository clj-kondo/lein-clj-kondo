(ns lein-clj-kondo.ci
  (:require
   [babashka.tasks :refer [shell]]
   [clojure.string :as string]))

(defn ^:private replace-in-file [file regex content]
  (as-> (slurp file) $
    (string/replace $ regex content)
    (spit file $)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn tag [& [tag]]
  (shell "git fetch origin")
  (shell "git pull origin HEAD")
  (replace-in-file "project.clj"
                   #"clj-kondo/lein-clj-kondo \"[0-9]+.[0-9]+.[0-9]+.*\""
                   (format "clj-kondo/lein-clj-kondo \"%s\"" tag))
  (replace-in-file "CHANGELOG.md"
                   #"## Unreleased"
                   (format "## Unreleased\n\n## %s" tag))
  (shell "git add project.clj CHANGELOG.md")
  (shell (format "git commit -m \"Release: %s\"" tag))
  (shell (str "git tag " tag))
  (shell "git push origin HEAD")
  (shell "git push origin --tags"))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn deploy [& _args]
  (shell "lein deploy clojars"))
