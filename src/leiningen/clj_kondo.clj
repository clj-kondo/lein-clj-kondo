(ns leiningen.clj-kondo
  (:require
   [clj-kondo.main :as kondo]
   [leiningen.classpath :as lein-classpath]
   [leiningen.core.main :as lein-core])
  (:import
   (java.io File)))

(defn ^:private run-kondo! [{:keys [source-paths test-paths]
                             {:keys [config]} :clj-kondo}
                            options]
  (let [args (or (not-empty options)
                 (when-let [v (->> [source-paths test-paths]
                                   (reduce into [])
                                   (filterv (fn [^String s]
                                              (-> s File. .exists)))
                                   (not-empty))]
                   (apply lein-core/info "Linting" v)
                   (into ["--lint"] v)))
        args (cond-> args
               config (conj "--config" config))
        exit-status (apply kondo/main args)]
    (when-not (zero? exit-status)
      (lein-core/exit exit-status))))

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
      (run-kondo! project options)
      (with-out-str
        (run-kondo! project options)))))
