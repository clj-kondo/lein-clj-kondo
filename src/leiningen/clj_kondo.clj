(ns leiningen.clj-kondo
  (:require
   [clj-kondo.main :as kondo]
   [leiningen.classpath :as lein-classpath]
   [leiningen.core.main :as lein-core]))

(defn ^:private run-kondo! [options]
  (let [exit-status (apply kondo/main options)]
    (when-not (= 0 exit-status)
      (System/exit exit-status))))

(defn parse-additional [project options]
  (mapv (fn [option]
          (if (= "$classpath" option)
            (lein-classpath/get-classpath-string project)
            option))
        options))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^:no-project-needed clj-kondo
  [project & options]
  (let [options (parse-additional project options)]
    (if lein-core/*info*
      (run-kondo! options)
      (with-out-str
        (run-kondo! options)))))
