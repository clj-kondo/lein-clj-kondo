(defproject com.github.clj-kondo/lein-clj-kondo "0.1.2"
  :description "Lein plugin to run clj-kondo"
  :url "https://clj-kondo.github.io/clj-kondo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :pedantic? :warn
  :dependencies [[clj-kondo/clj-kondo "2021.10.19"]]
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass
                                    :sign-releases false}]])
